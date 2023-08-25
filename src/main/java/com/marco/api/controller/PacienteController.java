package com.marco.api.controller;

import com.marco.api.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUmPaciente(@PathVariable Integer id){
        Paciente paciente = repository.getReferenceById(id);
        if(paciente.getAtivo()){
            return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        var page =repository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
        return  ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        if(paciente.getAtivo()){
            paciente.atualizar(dados);
            return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Integer id){
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }
}
