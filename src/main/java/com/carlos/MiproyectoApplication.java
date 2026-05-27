package com.carlos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase de arranque de Spring Boot.
 *
 * exclude = UserDetailsServiceAutoConfiguration.class:
 *   Spring Boot detecta Spring Security en el classpath y auto-configura
 *   un UserDetailsService en memoria con un usuario "user" y contraseña
 *   aleatoria. Al excluirla, solo nuestro SecurityConfig controla la
 *   autenticación vía JWT.
 */
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class MiproyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiproyectoApplication.class, args);
	}

	/**
	 * Bean temporal que genera hashes BCrypt para múltiples usuarios.
	 *
	 * Uso: los hashes impresos en consola se copian para insertarlos
	 * manualmente en MySQL como usuarios de prueba del sistema electoral.
	 *
	 * IMPORTANTE: BORRAR este método después de crear los usuarios.
	 * Este código se ejecuta en cada arranque de la aplicación y genera
	 * hashes nuevos cada vez (BCrypt usa salt aleatorio), por lo que
	 * los hashes impresos solo son útiles la primera vez.
	 *
	 * Alternativa más robusta para el futuro: crear un endpoint de
	 * administración POST /api/admin/users que reciba username y password
	 * en texto plano, genere el hash dentro de la aplicación, y guarde
	 * el usuario. Así no necesitas imprimir hashes ni copiar manualmente.
	 */
	@Bean
	public CommandLineRunner generarHashes() {
		return args -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			System.out.println("═══════════════════════════════════════");
			System.out.println("HASHES GENERADOS:");
			System.out.println("═══════════════════════════════════════");
			System.out.println("YaridP30 → " + encoder.encode("Pirateque2019"));
			System.out.println("═══════════════════════════════════════");
		};
	}
}