package com.br.spring_security.clinica.Request;

import com.br.spring_security.clinica.model.Medico;
import com.br.spring_security.clinica.model.Usuario;

import java.time.LocalDate;

public class MedicoRequest {

    private String nome;
    private Integer crm;
    private LocalDate dtInscricao;
    private Long idUsuario; // ID do usuário vinculado (opcional)

    public Medico toMedico() {
        Medico medico = new Medico();
        medico.setNome(this.nome);
        medico.setCrm(this.crm);
        medico.setDtInscricao(this.dtInscricao);

        if (this.idUsuario != null) {
            Usuario usuario = new Usuario();
            usuario.setId(this.idUsuario);
            medico.setUsuario(usuario);
        }

        return medico;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public LocalDate getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(LocalDate dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}