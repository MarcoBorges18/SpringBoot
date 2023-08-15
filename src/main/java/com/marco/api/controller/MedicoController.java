package com.marco.api.controller;

import com.marco.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController{

    @Autowired //Anotação que faz a injeção de dependência automática
    private MedicoRepository repository;

    @PostMapping
    @Transactional //Mantém uma conexão com o banco ativa
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAll(pageable).map(DadosListagemMedico::new);
        /*
        Método Pageable permite a paginação no retorno Get podendo limitar a quantidade de registros que irão aparecer na tela do usuário
        @PageableDefault(size = 10, sort = {"nome"} faz com que a requisição disparada seja exibida por padrão com 10 registros por página e sendo ordenado pelo nome
        Caso esses parâmetros sejam preenchidos na url da página, o método sera sobrescrito
        */
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }
}
