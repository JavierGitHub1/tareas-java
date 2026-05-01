package com.ejemplo.tareas.dto;

import java.time.LocalDateTime;

public record TareaDTO(Long id, String descripcion, boolean completada, LocalDateTime fechaCreacion, String nombreUsuario) {

}
