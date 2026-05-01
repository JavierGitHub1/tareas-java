package com.ejemplo.tareas.service;

import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return repository.findByNombre(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: " + username));

    }
}
