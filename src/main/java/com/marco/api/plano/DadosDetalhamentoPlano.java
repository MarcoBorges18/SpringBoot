package com.marco.api.plano;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoPlano(@NotNull Integer id, String nome, Double valor, Double desconto) {

    public DadosDetalhamentoPlano(PlanoDeSaude plano){
        this(plano.getId(), plano.getNome(), plano.getValorDoPlano(), plano.getDesconto());
    }
}
