package br.edu.insper.aps_2.movimentacao;

import java.time.LocalDate;

public class Movimentacao {

    private double valor;
    private String tipo;
    private LocalDate data;

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


