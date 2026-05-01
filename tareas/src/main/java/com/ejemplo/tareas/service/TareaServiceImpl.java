package com.ejemplo.tareas.service;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.dto.TareaRequest;
import com.ejemplo.tareas.mapper.ITareaMapper;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Service;

import  org.springframework.data.domain.Page;
import  org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TareaServiceImpl implements ITareaService{

    private final TareaRepository repository;

    private final UsuarioRepository usuarioRepository;

    private  final ITareaMapper mapper;

    public TareaServiceImpl(TareaRepository repository, UsuarioRepository usuarioRepository, ITareaMapper mapper){
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public  TareaDTO creaTarea(TareaRequest request){

        Usuario usuario= usuarioRepository.findById(request.usuarioId())
                .orElseThrow(()->new RuntimeException("No se puede crear tarea. Usuario no encontrado: " + request.usuarioId()));

        Tarea nuevaTarea = new Tarea(request.descripcion(), false, usuario);
        Tarea guardada = repository.save(nuevaTarea);


        return mapper.toDto(guardada);

    }



    @Override
    public List<TareaDTO> listarTareas() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public Page<TareaDTO> listarTareasPaginadas(Pageable pageable){
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    public List<TareaDTO> listarTareasPorUsuario(Long usuarioId){
        if(!usuarioRepository.existsById(usuarioId)){
            throw new RuntimeException("No se pueden listar Tareas. Usuario no encontrado: " + usuarioId);
        }

        List<Tarea> tareas = repository.findByUsuarioId(usuarioId);

        return  tareas.stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public TareaDTO obtenerTareaPorId(Long id){
        Tarea t =  repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ups ! No existe la tarea con ID: " + id));
        return mapper.toDto(t);
    }

    @Override
    public List<TareaDTO> buscarPorDescripcion(String palabra){
        return repository.findByDescripcionContaining(palabra).stream()
                .map(mapper::toDto).toList();
    }

    @Override
    public TareaDTO actualizarTarea(Long id, String nuevaDescripcion, boolean nuevaCompletada){
        Tarea tareaExistente = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("ups! No existe la tarea con ID:" + id));

        tareaExistente.setDescripcion(nuevaDescripcion);
        tareaExistente.setCompletada(nuevaCompletada);

        Tarea guardada =  repository.save(tareaExistente);

        return  mapper.toDto(guardada);
    }

    @Override
    public List<TareaDTO> listarPendientes(Long usuarioId){
        List<Tarea> pendientes = repository.buscarPendientesPorUsuario(usuarioId);

        return pendientes.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void eliminarTarea(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException(("No se puede borrar. No existe el ID: " + id));
        }
        repository.deleteById(id);
    }
}
