package com.marco.api.domain.paciente;

import com.marco.api.domain.endereco.Endereco;
import com.marco.api.domain.plano.PlanoDeSaude;
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
    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    @OneToOne
    @JoinColumn(name = "Plano_de_Saude", referencedColumnName = "id")
    private PlanoDeSaude plano;

    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizar(DadosAtualizarPaciente dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            endereco.atualizarinformacoes(dados.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
