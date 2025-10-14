package br.edu.insper.aps_2.cliente;

import br.edu.insper.aps_2.contaCorrente.ContaCorrente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private Float salario;
    @OneToOne(mappedBy = "cliente")
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
