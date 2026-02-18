package com.vitorbm.anota_tarefas_api.Services;

import com.vitorbm.anota_tarefas_api.Model.Tarefa;
import com.vitorbm.anota_tarefas_api.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getTarefa() {
        return (List<Tarefa>) tarefaRepository.findAll();
    }

    public Optional<Tarefa> getTarefabyId(UUID id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa putTarefa(UUID id, Tarefa tarefa) {
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setNome(tarefa.getNome());
            tarefaExistente.setData(tarefa.getData());
            tarefaExistente.setPrioridade(tarefa.getPrioridade());
            tarefaExistente.setAutor(tarefa.getAutor());
            return tarefaRepository.save(tarefaExistente);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + id));
    }

    public void deleteTarefa(UUID id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Tarefa não encontrada.");
        }
        tarefaRepository.deleteById(id);
    }
}
