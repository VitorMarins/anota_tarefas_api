package com.vitorbm.anota_tarefas_api.Repository;

import com.vitorbm.anota_tarefas_api.Model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {
    Optional<Usuario> findByNome(String nome);
}
