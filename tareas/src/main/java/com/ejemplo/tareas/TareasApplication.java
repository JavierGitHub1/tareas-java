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
	public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// 1. Leemos la clave de la variable de entorno
			String adminPass = System.getenv("ADMIN_PASSWORD");

			// 2. Validación de Senior: Si te olvidás de cargarla en Railway,
			// le ponemos una de emergencia para que la app no explote, pero te avisamos.
			if (adminPass == null || adminPass.isBlank()) {
				System.err.println("⚠️ ADVERTENCIA: La variable ADMIN_PASSWORD no está cargada. Usando clave por defecto.");
				adminPass = "clave_de_emergencia_123";
			}

			// 3. Buscamos a Javier. Si existe lo traemos, si no, creamos uno nuevo.
			Usuario javier = usuarioRepository.findByNombre("Javier")
					.orElseGet(() -> {
						Usuario nuevo = new Usuario();
						nuevo.setNombre("Javier");
						nuevo.setEmail("javier@mail.com");
						return nuevo;
					});

			// 4. Seteamos la clave (siempre se va a actualizar al arrancar)
			javier.setPassword(passwordEncoder.encode(adminPass));

			// 5. Guardamos (JPA hace el INSERT o el UPDATE solo)
			usuarioRepository.save(javier);

			System.out.println("🚀 CONFIGURACIÓN DE SEGURIDAD: Usuario 'Javier' sincronizado con éxito.");
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
