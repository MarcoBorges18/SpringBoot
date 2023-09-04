package com.marco.api.plano;

public record DadosListagemPlano(String nome, Double valor, Double desconto) {

    public DadosListagemPlano(PlanoDeSaude plano){
        this(plano.getNome(), plano.getValorDoPlano(), plano.getDesconto());
    }
}
