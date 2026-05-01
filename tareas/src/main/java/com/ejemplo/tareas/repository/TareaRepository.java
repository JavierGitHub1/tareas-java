package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TareaRepository  extends JpaRepository<Tarea, Long> {


    List<Tarea> findByDescripcionContaining(String palabra);

    List<Tarea> findByUsuarioId(Long usuarioId);

    @Query("select t from Tarea t where t.usuario.id=:idUsuario and t.completada = false order by t.fechaCreacion desc")
    List<Tarea> buscarPendientesPorUsuario(@Param("idUsuario") long idUsuario);
}
