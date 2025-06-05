package com.br.spring_security.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ApiController {

    // Endpoint de teste da API
    @GetMapping("/api/home")
    public ResponseEntity<String> apiHome() {
        return ResponseEntity.ok("Bem-vindo à API da clínica");
    }

    @GetMapping("/api/login")
    public ResponseEntity<String> apiLogin() {
        return ResponseEntity.ok("Login pela API");
    }

    @GetMapping("/api/login-error")
    public ResponseEntity<Map<String, String>> apiLoginError() {
        return ResponseEntity.status(401).body(Map.of(
                "alerta", "erro",
                "titulo", "Credenciais Inválidas",
                "texto", "Login ou senha incorretos, tente novamente.",
                "subtexto", "Acesso permitido apenas para cadastros já ativados."
        ));
    }

    @GetMapping("/api/acesso-negado")
    public ResponseEntity<Map<String, String>> acessoNegado() {
        return ResponseEntity.status(403).body(Map.of(
                "titulo", "Acesso negado",
                "texto", "Você não tem permissão para acessar este recurso."
        ));
    }
}