package com.br.spring_security.clinica.service;

import java.util.List;
import java.util.Optional; // Importe Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; // Importe PasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.spring_security.clinica.model.Usuario; // Certifique-se de importar a classe Usuario
import com.br.spring_security.clinica.model.Perfil; // Certifique-se de importar a classe Perfil
import com.br.spring_security.clinica.repository.UsuarioRepository;
import com.br.spring_security.clinica.Request.UsuarioRequest; // Importe UsuarioRequest se necessário para conversão

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder; // Adicione PasswordEncoder para criptografar senhas

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // --- Métodos de CRUD ---

    @Transactional // Para operações de escrita
    public Usuario salvar(Usuario usuario) {
        // Criptografar a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    @Transactional(readOnly = true) // Para operações de leitura
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true) // Para operações de leitura
    public Usuario obterPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElse(null); // Retorna null se não encontrar
    }

    @Transactional // Para operações de escrita
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        // Busca o usuário existente
        Optional<Usuario> existingUsuarioOptional = repository.findById(id);

        if (existingUsuarioOptional.isPresent()) {
            Usuario existingUsuario = existingUsuarioOptional.get();

            // Atualiza apenas os campos necessários.
            // Para senhas, você deve ter um endpoint separado ou lógica para não sobrescrever
            // uma senha criptografada com uma senha em texto plano.
            // Aqui, vamos assumir que a senha virá já tratada ou não será atualizada por este método.
            // Se a senha for enviada no request, ela deve ser criptografada novamente.
            if (usuarioAtualizado.getEmail() != null) {
                existingUsuario.setEmail(usuarioAtualizado.getEmail());
            }
            if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
                // Se a senha foi enviada, criptografe-a
                existingUsuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
            }
            if (usuarioAtualizado.getPerfis() != null && !usuarioAtualizado.getPerfis().isEmpty()) {
                existingUsuario.setPerfis(usuarioAtualizado.getPerfis());
            }
            // ... outros campos que você pode querer atualizar

            existingUsuario.setId(id); // Garante que o ID correto seja usado
            return repository.save(existingUsuario);
        } else {
            // Lança uma exceção se o usuário não for encontrado para atualização
            throw new UsernameNotFoundException("Usuário com ID " + id + " não encontrado para atualização.");
        }
    }

    @Transactional // Para operações de escrita
    public void deletar(Long id) {
        // Opcional: verificar se o ID existe antes de tentar deletar
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            // Lança uma exceção se o usuário não for encontrado para exclusão
            throw new UsernameNotFoundException("Usuário com ID " + id + " não encontrado para exclusão.");
        }
    }

    // --- Métodos de Spring Security (mantidos) ---

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
        );
    }

    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getDescricao();
        }
        return authorities;
    }
}