package com.marco.api.domain.paciente;

import com.marco.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        @Email
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone,
        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        @NotNull(message = "Endereço é obrigatório")
        @Valid
        DadosEndereco endereco
) {
}
