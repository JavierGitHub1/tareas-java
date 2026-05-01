package com.ejemplo.tareas.service;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.dto.TareaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITareaService {

    TareaDTO creaTarea(TareaRequest request);
    List<TareaDTO> listarTareas();
    Page<TareaDTO> listarTareasPaginadas(Pageable pageable);
    TareaDTO obtenerTareaPorId(Long id);
    List<TareaDTO> buscarPorDescripcion(String palabra);
    TareaDTO actualizarTarea(Long id, String nuevaDescripcion, boolean nuevaCompletada);
    void eliminarTarea(Long id);
    List<TareaDTO> listarTareasPorUsuario(Long usuarioId);
    List<TareaDTO> listarPendientes(Long usuarioId);
}
