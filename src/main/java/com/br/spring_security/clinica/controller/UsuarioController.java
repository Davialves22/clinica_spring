package com.br.spring_security.clinica.controller;

import com.br.spring_security.clinica.model.Medico;
import com.br.spring_security.clinica.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    //abrir pagina de dados pessoais de medicos pelo medico
    @GetMapping({"/novoCadastro/usuario"})
    public String cadastroPorAdminParaTodos(Usuario usuario){
        return "usuario/cadastro";
    }
}