package com.ejemplo.tareas;


import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.dto.TareaRequest;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import com.ejemplo.tareas.service.TareaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import  static  org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;


    @InjectMocks
    private TareaServiceImpl tareaService;

    @Test
    void debeCrearTareaExitosamente(){
        Usuario javier= new Usuario("Javier", "javier@gmail.com");
        TareaRequest request = new TareaRequest("Ir al gimnasio", 1L);

        when(usuarioRepository.findById(1L)).thenReturn (Optional.of(javier));
        when(tareaRepository.save(any(Tarea.class))).thenAnswer(i -> i.getArguments()[0]);

        TareaDTO resultado = tareaService.creaTarea(request);

        assertNotNull(resultado);
        assertEquals("Ir al gimnasio", resultado.descripcion());
        assertEquals("Javier", resultado.nombreUsuario());
        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }

    @Test
    void debeCrearTareaVinculadaAUser(){

        // 1. preparación (Given>
        // creamos al dueño (objeto real)
        Usuario usuario = new Usuario("Javier" ,  "javier@gmail.com");

        usuario.setPassword("$2a$10$vI8Z9.fH0uGv1oW.m8Loe.1zY6zX8M6o9B1RkS5M7F6X5l3K2L1M.");

        // Creamos el pedido (DTO)
        TareaRequest request = new TareaRequest("Ir al gym", 1L);


        when (usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        when (tareaRepository.save(any(Tarea.class))).thenAnswer(i -> i.getArguments()[0]);

        TareaDTO resultado = tareaService.creaTarea(request);

        // 3. Verificación (then)
        assertNotNull(resultado);
        assertEquals("Ir al gym", resultado.descripcion());
        assertEquals("Javier", resultado.nombreUsuario());


        // Verificamos que se consultaron AMBOS repositorios
        verify(usuarioRepository, times(1)).findById(1L);
        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }

}
