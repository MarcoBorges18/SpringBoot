package com.marco.api.controller;

import com.marco.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
public class MedicoController{

    @Autowired //Anotação que faz a injeção de dependência automática
    private MedicoRepository repository;

    @PostMapping
    @Transactional //Mantém uma conexão com o banco ativa
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        //UriComponentsBuilder é um método que fica responsável por criar um header para o teu Create com o response 201 no HTTP

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
        /*
        Método Pageable permite a paginação no retorno Get podendo limitar a quantidade de registros que irão aparecer na tela do usuário
        @PageableDefault(size = 10, sort = {"nome"} faz com que a requisição disparada seja exibida por padrão com 10 registros por página e sendo ordenado pelo nome
        Caso esses parâmetros sejam preenchidos na url da página, o método sera sobrescrito
        */
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUmMedico(@PathVariable Integer id){
        var medico = repository.getReferenceById(id);
        if(medico.getAtivo()){
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        var medico = repository.getReferenceById(dados.id());
        if(medico.getAtivo()){
            medico.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
            //ResponseEntity.ok retorna o valor 200 no HTTP, adicionar os parâmetros dentro dele faz com que tenha retorno no body
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity ativarMedico(@PathVariable Integer id){
        Medico medico = repository.getReferenceById(id);
        if(!medico.getAtivo()){
            medico.ativar();
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Integer id){
        //ResponseEntity é um metodo que controla o retorno HTTP para que não fique somente retorno 200
        var medico = repository.getReferenceById(id);
        medico.excluir();

        //ResponseEntity.noContent().build(); faz com que o HTTP retorne 204 No content que significa que foi processado corretamente mas não tem retorno
        return ResponseEntity.noContent().build();
    }
}
