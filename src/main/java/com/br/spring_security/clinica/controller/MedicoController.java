package com.br.spring_security.clinica.controller;

import com.br.spring_security.clinica.Request.MedicoRequest;
import com.br.spring_security.clinica.controller.docs.MedicoControllerDocs;
import com.br.spring_security.clinica.model.Medico;
import com.br.spring_security.clinica.service.MedicoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importa HttpStatus
import org.springframework.http.MediaType; // Importa MediaType
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos/v1")
@CrossOrigin

@Tag(name = "Médicos", description = "Endpoints para gerenciamento de médicos na clínica")
public class MedicoController implements MedicoControllerDocs {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Override
    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    })
    public ResponseEntity<Medico> salvar(@RequestBody MedicoRequest request) {
        Medico medico = medicoService.salvar(request.toMedico());
        return new ResponseEntity<>(medico, HttpStatus.CREATED); // Alterado para new ResponseEntity
    }

    @Override
    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    })
    public List<Medico> listarTodos() {
        return medicoService.listarTodos();
    }

    @Override
    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    })
    public ResponseEntity<Medico> obterPorId(@PathVariable Long id) {
        Medico medico = medicoService.obterPorId(id);
        // Mantém a lógica de retorno 200 OK ou 404 Not Found
        return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = { // PUT também pode ter consumes
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    })
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody MedicoRequest request) {
        medicoService.atualizar(id, request.toMedico());
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_YAML_VALUE
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}