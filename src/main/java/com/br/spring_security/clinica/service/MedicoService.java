package com.br.spring_security.clinica.service;

import com.br.spring_security.clinica.model.Medico;
import com.br.spring_security.clinica.repository.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico obterPorId(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.orElse(null);
    }

    public void atualizar(Long id, Medico medicoAtualizado) {
        medicoAtualizado.setId(id);
        medicoRepository.save(medicoAtualizado);
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }
}