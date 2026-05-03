package com.ejemplo.tareas.mapper;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.model.Usuario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-25T18:17:28-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ITareaMapperImpl implements ITareaMapper {

    @Override
    public TareaDTO toDto(Tarea tarea) {
        if ( tarea == null ) {
            return null;
        }

        String nombreUsuario = null;
        Long id = null;
        String descripcion = null;
        boolean completada = false;
        LocalDateTime fechaCreacion = null;

        nombreUsuario = tareaUsuarioNombre( tarea );
        id = tarea.getId();
        descripcion = tarea.getDescripcion();
        completada = tarea.isCompletada();
        fechaCreacion = tarea.getFechaCreacion();

        TareaDTO tareaDTO = new TareaDTO( id, descripcion, completada, fechaCreacion, nombreUsuario );

        return tareaDTO;
    }

    @Override
    public List<TareaDTO> toDtoList(List<Tarea> tareas) {
        if ( tareas == null ) {
            return null;
        }

        List<TareaDTO> list = new ArrayList<TareaDTO>( tareas.size() );
        for ( Tarea tarea : tareas ) {
            list.add( toDto( tarea ) );
        }

        return list;
    }

    private String tareaUsuarioNombre(Tarea tarea) {
        Usuario usuario = tarea.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getNombre();
    }
}
