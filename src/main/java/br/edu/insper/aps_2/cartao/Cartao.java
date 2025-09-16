package br.edu.insper.aps_2.cartao;

import br.edu.insper.aps_2.contaCorrente.ContaCorrente;

import java.time.LocalDate;

public class Cartao {

    private String numeroCartao;
    private String tipo;
    private LocalDate validade;
    private String status;
    private ContaCorrente contaCorrente;

    public Cartao(String numeroCartao, String tipo, LocalDate validade, String status, ContaCorrente contaCorrente) {
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.validade = validade;
        this.status = "ATIVO";
        this.contaCorrente = contaCorrente;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExpired() {
        return validade.isAfter(LocalDate.now());
    }

    // verificar cpf do cliente e número do cartão
    public void cancelaCartao() {
        status = "CANCELADO";
    }

}
