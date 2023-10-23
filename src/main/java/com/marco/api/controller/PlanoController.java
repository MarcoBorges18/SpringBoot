package com.marco.api.controller;

import com.marco.api.domain.plano.DadosListagemPlano;
import com.marco.api.domain.plano.PlanoDeSaude;
import com.marco.api.domain.plano.PlanoRepository;
import com.marco.api.domain.plano.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/planos")
@RestController
public class PlanoController {

    @Autowired
    PlanoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPlano dados, UriComponentsBuilder uriBuilder){
        PlanoDeSaude plano = new PlanoDeSaude(dados);
        repository.save(plano);

        var uri = uriBuilder.path("/planos/{id}").buildAndExpand(plano.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPlano(plano));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPlano>> listarTodos(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAllByDisponivelTrue(pageable).map(DadosListagemPlano::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable Integer id){
        PlanoDeSaude plano = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemPlano(plano));
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterar(@RequestBody @Valid DadosDetalhamentoPlano dados){
        PlanoDeSaude plano = repository.getReferenceById(dados.id());
        plano.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPlano(plano));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarPlano(@PathVariable Integer id){
        PlanoDeSaude plano = repository.getReferenceById(id);
        plano.ativar();
        return ResponseEntity.ok(new DadosDetalhamentoPlano(plano));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarPlano(@PathVariable Integer id){
        PlanoDeSaude plano = repository.getReferenceById(id);
        plano.inativar();
        return ResponseEntity.noContent().build();
    }
}
