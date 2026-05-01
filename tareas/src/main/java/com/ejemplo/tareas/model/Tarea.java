package com.ejemplo.tareas.model;


import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE tarea SET eliminado = true WHERE id=?")
@SQLRestriction("eliminado = false")
public class Tarea{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private boolean completada;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    private boolean eliminado = false;

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getFechaCreacion(){
        return fechaCreacion;
    }

    public Tarea() {

    }

    public Tarea(String descripcion, boolean completada){
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public Tarea(String descripcion, boolean completada, Usuario usuario){
        this.descripcion = descripcion;
        this.completada = completada;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
