package com.marco.api.domain.paciente;

import com.marco.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Integer id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
