package com.marco.api.domain.paciente;

public record DadosListagemPaciente(Integer id, String nome, String email, String cpf) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}