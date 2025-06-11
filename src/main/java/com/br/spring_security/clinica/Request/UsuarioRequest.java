package com.br.spring_security.clinica.Request;

import com.br.spring_security.clinica.model.Perfil;
import com.br.spring_security.clinica.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    private List<Long> perfisIds; // IDs dos perfis

    /**
     * Converte o UsuarioRequest para uma entidade Usuario.
     * Define 'ativo' como false por padrão e atribui perfis pelos IDs.
     * @return Uma nova instância de Usuario.
     */
    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha); // A senha será criptografada no serviço

        if (this.perfisIds != null && !this.perfisIds.isEmpty()) {
            usuario.setPerfis(this.perfisIds.stream()
                    .map(Perfil::new) // Construtor Perfil(Long id) é assumido
                    .collect(Collectors.toList()));
        }
        usuario.setAtivo(false); // Por padrão, usuário é criado como inativo
        return usuario;
    }
}