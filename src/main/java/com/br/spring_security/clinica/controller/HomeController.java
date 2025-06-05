package com.br.spring_security.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// ----- MODO 1: Ativar esta anotação para páginas -----
// @Controller

// ----- MODO 2: Ativar esta anotação para API JSON -----
@RestController
public class HomeController {

    // ----- PÁGINA HTML -----
    /*
    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // Retorna view home.html (ex: Thymeleaf)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna view login.html
    }

    @GetMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Credenciais Inválidas");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
        return "login";
    }
    */

    // ----- MODO JSON / API REST -----

    @GetMapping({"/", "/home"})
    public ResponseEntity<String> apiHome() {
        return ResponseEntity.ok("Bem-vindo à API da clínica");
    }

    @GetMapping("/login")
    public ResponseEntity<String> apiLogin() {
        return ResponseEntity.ok("Página de login (API)");
    }

    @GetMapping("/login-error")
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