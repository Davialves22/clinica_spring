package com.br.spring_security.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap; // Considere usar um Map ou DTO para respostas de API
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Endpoint da API para a "home" da API.
    // O prefixo '/api' evita conflito com a página web principal.
    @GetMapping({"/api", "/api/home"})
    public ResponseEntity<String> apiHome() {
        return ResponseEntity.ok("Bem-vindo à API da clínica");
    }

    // Endpoint da API para login (pode ser usado para exibir informações de login da API)
    @GetMapping("/api/login")
    public ResponseEntity<String> apiLogin() {
        return ResponseEntity.ok("Endpoint de login da API");
    }

    // Endpoint da API para erro de login.
    // Para APIs, geralmente retorna-se um JSON com os detalhes do erro.
    // Um ModelMap pode funcionar, mas um Map<String, Object> ou um DTO de erro seria mais idiomático para REST.
    @GetMapping("/api/login-error")
    public ResponseEntity<?> apiLoginError() {
        return ResponseEntity.status(401).body(
                new ModelMap() {{
                    put("alerta", "erro");
                    put("titulo", "Credenciais Inválidas");
                    put("texto", "Login ou senha incorretos, tente novamente.");
                    put("subtexto", "Acesso permitido apenas para cadastros já ativados.");
                }}
        );
    }
}