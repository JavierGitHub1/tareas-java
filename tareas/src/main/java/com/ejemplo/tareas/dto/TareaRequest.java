package com.ejemplo.tareas.dto;

import com.ejemplo.tareas.validation.NoPalabrasProhibidas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TareaRequest (@NotBlank(message = "La descripción es obligatoria")
                            @NoPalabrasProhibidas
                               @Size(min=3, message = "Debe tener al menos 3 caracteres")
                               String descripcion,
                            @NotNull(message = "El Id de usuario es obligatorio") Long usuarioId){


}


