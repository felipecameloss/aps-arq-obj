package br.edu.insper.aps_2.movimentacao;

import br.edu.insper.aps_2.contaCorrente.model.ContaCorrente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double valor;
    private String tipo;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private ContaCorrente contaCorrente;

    public Movimentacao(double valor, String tipo, LocalDate data) {
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

}


