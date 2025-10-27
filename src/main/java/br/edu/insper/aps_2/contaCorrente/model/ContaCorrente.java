package br.edu.insper.aps_2.contaCorrente.model;

import br.edu.insper.aps_2.cartao.model.Cartao;
import br.edu.insper.aps_2.cliente.model.Cliente;
import br.edu.insper.aps_2.movimentacao.Movimentacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String agencia;
    @Column(nullable = false,  unique = true)
    private String numero;
    private double saldo;
    private double limite;

    @JsonIgnore
    @JoinColumn(name = "id_cliente")
    @OneToOne
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "contaCorrente")
    private final ArrayList<Movimentacao> movimentacoes = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "contaCorrente")
    private ArrayList<Cartao> cartoes = new ArrayList<>();

    // Construtor vazio necessário pro Spring desserializar JSON
    public ContaCorrente() {}

    // Construtor completo (caso queira instanciar manualmente)
    public ContaCorrente(String agencia, String numero, double saldo, double limite, Cliente cliente, ArrayList<Cartao> cartoes) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.cliente = cliente;
        this.cartoes = cartoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCartoes(ArrayList<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    // getters e setters
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public void saque(double valor) {

        if (saldo + limite >= valor) {
            saldo -= valor;
//           Salvando a movimentação
            movimentacoes.add(new Movimentacao(valor, "Saque", LocalDate.now()));
        } else {
            throw new RuntimeException("Saldo insuficiente para realizar o saque");
        }

    }

    public void deposito(double valor) {

        if (valor > 0) {
            saldo += valor;
//            Salvando a movimentação
            movimentacoes.add(new Movimentacao(valor, "Depósito", LocalDate.now()));
        } else {
            throw new RuntimeException("Valor de depósito inválido");
        }
    }

    public void adicionaCartao(Cartao cartao) {
        cartoes.add(cartao);
    }

    public ArrayList<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public ArrayList<Cartao> getCartoes() {
        return cartoes;
    }
}

