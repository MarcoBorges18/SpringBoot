package com.marco.api.domain.paciente;

import com.marco.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizarPaciente(Integer id, String nome, String telefone, @Valid DadosEndereco endereco) {
}
