package com.br.spring_security.clinica.Request;

import com.br.spring_security.clinica.model.Perfil;
import com.br.spring_security.clinica.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 255, message = "O e-mail não pode exceder 255 caracteres")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "O campo ativo deve ser informado.")
    private Boolean ativo;

    @Size(max = 6, message = "O código verificador deve ter no máximo 6 caracteres.")
    private String codigoVerificador;

    private List<Long> perfisIds; // IDs dos perfis

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha); // A senha será criptografada no serviço
        usuario.setAtivo(this.ativo);
        usuario.setCodigoVerificador(this.codigoVerificador);

        if (this.perfisIds != null && !this.perfisIds.isEmpty()) {
            usuario.setPerfis(this.perfisIds.stream()
                    .map(Perfil::new)
                    .collect(Collectors.toList()));
        }
        return usuario;
    }
}