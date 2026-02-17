package com.vitorbm.anota_tarefas_api.Controllers;

import com.vitorbm.anota_tarefas_api.Model.Tarefa;
import com.vitorbm.anota_tarefas_api.Services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> getTarefa() {
        return tarefaService.getTarefa();
    }

    @GetMapping("/{id}")
    public Optional<Tarefa> getTarefabyId(@RequestParam UUID id) {
        return tarefaService.getTarefabyId(id);
    }

    @PostMapping
    public ResponseEntity<Tarefa> createTarefa(Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.createTarefa(tarefa));
    }

    @PutMapping("/{id}")
    public Tarefa putTarefa(@PathVariable UUID id,@RequestBody Tarefa tarefa ) {
        return tarefaService.putTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void deleteTarefa(@PathVariable UUID id) {
        tarefaService.deleteTarefa(id);
    }
}
