package com.br.spring_security.clinica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    // AGORA INCLUIDO NA DOCUMENTAÇÃO DO SWAGGER UI
    @GetMapping({"/api/v1", "/api/home/v1"}) // Adicionado '/v1'
    public ResponseEntity<String> apiHome() {
        return ResponseEntity.ok("Bem-vindo à API da clínica!");
    }

    // AGORA INCLUIDO NA DOCUMENTAÇÃO DO SWAGGER UI
    @GetMapping("/api/login/v1") // Adicionado '/v1'
    public ResponseEntity<String> apiLogin() {
        return ResponseEntity.ok("Endpoint informativo de login da API. Use POST para autenticar.");
    }

    // AGORA INCLUIDO NA DOCUMENTAÇÃO DO SWAGGER UI
    @GetMapping("/api/login-error/v1") // Adicionado '/v1'
    public ResponseEntity<?> apiLoginError() {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.UNAUTHORIZED.value());
        errorDetails.put("error", "Unauthorized");
        errorDetails.put("message", "Credenciais Inválidas");
        errorDetails.put("details", "Login ou senha incorretos. Acesso apenas para cadastros ativados.");
        errorDetails.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }
}