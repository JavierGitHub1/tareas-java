package com.ejemplo.tareas.mapper;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.model.Tarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITareaMapper {

    @Mapping(source = "usuario.nombre", target = "nombreUsuario")
    TareaDTO toDto(Tarea tarea);


    List<TareaDTO> toDtoList(List<Tarea> tareas);

}
