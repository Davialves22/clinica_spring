package com.br.spring_security.clinica.controller;

import com.br.spring_security.clinica.Request.MedicoRequest;
import com.br.spring_security.clinica.model.Medico;
import com.br.spring_security.clinica.service.MedicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> salvar(@RequestBody MedicoRequest request) {
        Medico medico = medicoService.salvar(request.toMedico());
        return ResponseEntity.status(201).body(medico);
    }

    @GetMapping
    public List<Medico> listarTodos() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obterPorId(@PathVariable Long id) {
        Medico medico = medicoService.obterPorId(id);
        return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody MedicoRequest request) {
        medicoService.atualizar(id, request.toMedico());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}