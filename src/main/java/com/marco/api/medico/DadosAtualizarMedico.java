package com.marco.api.medico;

import com.marco.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Integer id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
