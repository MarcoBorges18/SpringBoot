package com.marco.api.controller;

import com.marco.api.paciente.DadosCadastroPaciente;
import com.marco.api.paciente.DadosListagemPaciente;
import com.marco.api.paciente.Paciente;
import com.marco.api.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAll(pageable).map(DadosListagemPaciente::new);
    }
}
