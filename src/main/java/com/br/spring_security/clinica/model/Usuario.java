package com.br.spring_security.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.br.spring_security.clinica.model.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios", indexes = {
        @Index(name = "idx_usuario_email", columnList = "email") // Índice para buscas por e-mail
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Usuario extends AbstractEntity {

    @Email(message = "Email inválido.")
    @NotBlank(message = "O email é obrigatório.")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuarios_tem_perfis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfis; // Perfis atribuídos ao usuário


    @NotNull(message = "O campo ativo deve ser informado.")
    @Column(name = "ativo", nullable = false, columnDefinition = "BOOLEAN")
    private boolean ativo;

    @Size(max = 6, message = "O código verificador deve ter no máximo 6 caracteres.")
    @Column(name = "codigo_verificador", length = 6)
    private String codigoVerificador;

    public Usuario(Long id) {
        super.setId(id); // Construtor com ID
    }

    public Usuario(String email) {
        this.email = email; // Construtor com email
    }

    // Adiciona um perfil ao usuário a partir do tipo (enum)
    public void addPerfil(PerfilTipo tipo) {
        if (this.perfis == null) {
            this.perfis = new ArrayList<>();
        }
        this.perfis.add(new Perfil(tipo.getCod()));
    }
}