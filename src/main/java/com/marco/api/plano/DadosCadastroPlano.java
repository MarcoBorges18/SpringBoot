package com.marco.api.plano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosCadastroPlano(
        @NotBlank
        String nome,
        @PositiveOrZero
        Double valorDoPlano,
        @PositiveOrZero
        Double descontoDoPlano
) { }
