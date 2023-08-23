package com.marco.api.paciente;

import com.marco.api.endereco.DadosEndereco;
import com.marco.api.endereco.Endereco;
import jakarta.validation.Valid;

public record DadosAtualizarPaciente(Integer id, String nome, String telefone, @Valid DadosEndereco endereco) {
}
