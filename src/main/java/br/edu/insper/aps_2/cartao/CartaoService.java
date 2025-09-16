package br.edu.insper.aps_2.cartao;

import br.edu.insper.aps_2.contaCorrente.ContaCorrente;
import br.edu.insper.aps_2.contaCorrente.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class CartaoService {

    @Autowired
    ContaCorrenteService contaCorrenteService;

    private final HashMap<String, Cartao> cartoes = new HashMap<>();

    public Cartao emiteCartao(Cartao cartao) {

        ContaCorrente contaCorrente = contaCorrenteService.buscaConta(cartao.getContaCorrente().getNumero());

        if (contaCorrente == null) {
            throw new RuntimeException("Conta corrente não encontrada com o número: " + cartao.getContaCorrente().getNumero());
        }

        contaCorrente.adicionaCartao(cartao);
        cartoes.put(cartao.getNumeroCartao(), cartao);

        return cartao;

    }

    public Collection<Cartao> listaCartoes() {
        return cartoes.values();
    }

    public ArrayList<Cartao> listaCartoesConta(ContaCorrente conta) {
        return conta.getCartoes();
    }

    public void cancelaCartao(Cartao cartao) {
        cartao.cancelaCartao();
    }

    public boolean verificaCartaoAtivo(Cartao cartao) {
        return cartao.getStatus().equals("ATIVO");
    }

}
