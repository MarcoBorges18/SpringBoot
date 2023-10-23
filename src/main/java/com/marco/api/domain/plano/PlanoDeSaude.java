package com.marco.api.domain.plano;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Plano_de_Saude")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlanoDeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Double valorDoPlano;
    private Double desconto;
    private Boolean disponivel;

    public PlanoDeSaude(DadosCadastroPlano dados){
        this.nome = dados.nome();
        this.valorDoPlano = dados.valorDoPlano();
        this.desconto = dados.descontoDoPlano();
        this.disponivel = true;
    }


    public void atualizar(DadosDetalhamentoPlano dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.valor() != null){
            this.valorDoPlano = dados.valor();
        }
        if(dados.desconto() != null){
            this.desconto = dados.desconto();
        }
    }

    public void inativar() {
        this.disponivel = false;
    }

    public void ativar() {
        this.disponivel = true;
    }
}
