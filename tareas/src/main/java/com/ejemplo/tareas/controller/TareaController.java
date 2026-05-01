package com.ejemplo.tareas.controller;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.dto.TareaRequest;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.service.ITareaService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final ITareaService service;

    // inyección por constructor
    public TareaController(ITareaService service){
        this.service = service;

    }

    // crea las tareas get
    @GetMapping("/paginado")
    public Page<TareaDTO> listarPaginado(@ParameterObject Pageable pageable){
        return service.listarTareasPaginadas(pageable);
    }

    // GET http://localhost:8080/api/tareas/:id
    @GetMapping("/{id}")
    public TareaDTO obtenerUna(@PathVariable Long id){
        return  service.obtenerTareaPorId(id);
    }

    // GET http://localhost:8080/api/tareas/buscar?filtro=josefa
    @GetMapping("/buscar")
    public List<TareaDTO> buscar(@RequestParam String filtro){
        return service.buscarPorDescripcion(filtro);
    }

    @GetMapping("/usuarios/{usuarioId}/tareas")
    public List<TareaDTO> listarPorUsuario(@PathVariable Long usuarioId){
        return service.listarTareasPorUsuario(usuarioId);
    }

    // crea las tareas post
    // usamos @RequestParam para pasar la descripción por la URL por ahora
    @PostMapping
    public TareaDTO crear(@Valid @RequestBody TareaRequest request){
        return service.creaTarea(request);
    }

    @GetMapping("/usuarios/{usuarioId}/pendientes")
    public List<TareaDTO> listarPendientes(@PathVariable Long usuarioId){
        return service.listarPendientes(usuarioId);
    }


    // PUT http://localhost:8080/api/tareas/1
    @PutMapping("/{id}")
    public TareaDTO actualizar(@PathVariable Long id, @RequestBody TareaRequest request){
        return service.actualizarTarea(id, request.descripcion(), false);
    }

    // DEL http://localhost:8080/api/tareas/1
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id){
        service.eliminarTarea(id);
        return "Tarea eliminada correctamente";
    }
}
