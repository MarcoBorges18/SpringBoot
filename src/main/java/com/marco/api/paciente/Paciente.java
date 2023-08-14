package com.marco.api.paciente;

import com.marco.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nome;
    String email;
    String cpf;
    String telefone;
    @Embedded
    Endereco endereco;

    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }
}
