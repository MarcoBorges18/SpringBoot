package com.marco.api.domain.medico;

import com.marco.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Integer id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
