package com.br.spring_security.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    // Página inicial
    @GetMapping({"/", "/home"})
    public String home() {
        return "home"; // Aponta para home.html no templates
    }

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login"; // Aponta para login.html no templates
    }

    // Página de erro de login
    @GetMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Credenciais Inválidas");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
        return "login"; // Volta para login.html com mensagens de erro
    }

    // Página de acesso negado
    @GetMapping("/acesso-negado")
    public String acessoNegado(ModelMap model) {
        model.addAttribute("titulo", "Acesso negado");
        model.addAttribute("texto", "Você não tem permissão para acessar esta página.");
        return "acesso-negado"; // acesso-negado.html
    }
}