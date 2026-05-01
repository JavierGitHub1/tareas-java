package com.ejemplo.tareas;

import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class TareasApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("zona horaria forzada a UTC para evitar conflictos.");
	}


	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (usuarioRepository.findByNombre("Javier").isEmpty()) {
				Usuario javier = new Usuario();
				javier.setNombre("Javier");
				javier.setEmail("javier@mail.com");
				// Usamos el passwordEncoder para que la clave sea válida para el portero
				javier.setPassword(passwordEncoder.encode("admin123"));
				usuarioRepository.save(javier);
				System.out.println("✅ Usuario 'Javier' creado automáticamente para el test.");
			}
		};
	}

	/*@Bean
	CommandLineRunner resetPassword(UsuarioRepository repo, PasswordEncoder encoder) {
		return args -> {
			// En lugar de borrar, buscamos al usuario existente
			Usuario javier = repo.findByNombre("Javier").orElseGet(() -> {
				// Si no existe, creamos uno nuevo
				Usuario nuevo = new Usuario();
				nuevo.setNombre("Javier");
				nuevo.setEmail("javier@mail.com");
				return nuevo;
			});

			// Le ponemos la clave correcta (hasheada por Spring)
			javier.setPassword(encoder.encode("admin123"));

			// Guardamos los cambios (esto hace un UPDATE en SQL)
			repo.save(javier);

			System.out.println("✅ ¡PASSWORD DE JAVIER ACTUALIZADA CORRECTAMENTE!");
		};
	}*/
}
