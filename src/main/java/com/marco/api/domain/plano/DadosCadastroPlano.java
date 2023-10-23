package com.marco.api.domain.plano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosCadastroPlano(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @PositiveOrZero(message = "Valor do plano é obrigatório")
        Double valorDoPlano,
        @PositiveOrZero(message = "Desconto do plano é obrigatório")
        Double descontoDoPlano
) { }
