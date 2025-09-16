package br.edu.insper.aps_2.cliente;

import br.edu.insper.aps_2.contaCorrente.ContaCorrente;

import java.time.LocalDate;

public class Cliente {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Float salario;
    private ContaCorrente contaCorrente;

    public Cliente(String cpf, String nome, LocalDate dataNascimento, Float salario, ContaCorrente contaCorrente) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.contaCorrente = contaCorrente;
    }

    public Cliente() {

    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Float getSalario() {
        return salario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
}
