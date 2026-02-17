package com.vitorbm.anota_tarefas_api.Services;

import com.vitorbm.anota_tarefas_api.Model.Usuario;
import com.vitorbm.anota_tarefas_api.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
    public Optional<Usuario> getUsuarioById(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        String senhaSegura = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaSegura);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(UUID id, Usuario usuario) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(usuario.getNome());
            if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
                usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
            }
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }

    public void deleteUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}
