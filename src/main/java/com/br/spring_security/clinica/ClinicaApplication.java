package com.br.spring_security.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ClinicaApplication {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String adminHash = encoder.encode("admin");
		String medicoHash = encoder.encode("medico1");
		String pacienteHash = encoder.encode("paciente1");

		System.out.println("Hash senha ADMIN: " + adminHash);
		System.out.println("Hash senha MEDICO: " + medicoHash);
		System.out.println("Hash senha PACIENTE: " + pacienteHash);

		SpringApplication.run(ClinicaApplication.class, args);
	}
}