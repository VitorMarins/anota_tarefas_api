package com.vitorbm.anota_tarefas_api.Repository;

import com.vitorbm.anota_tarefas_api.Model.Tarefa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, UUID> {
}
