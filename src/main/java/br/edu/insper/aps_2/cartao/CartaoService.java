package br.edu.insper.aps_2.cartao;

import br.edu.insper.aps_2.contaCorrente.ContaCorrente;
import br.edu.insper.aps_2.contaCorrente.ContaCorrenteRepository;
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

    @Autowired
    private ContaCorrenteRepository  contaCorrenteRepository;

    @Autowired
    private CartaoRepository  cartaoRepository;

    public Cartao emiteCartao(Cartao cartao) {

        ContaCorrente contaCorrente = contaCorrenteService.buscaConta(cartao.getContaCorrente().getNumero());

        if (contaCorrente == null) {
            throw new RuntimeException("Conta corrente não encontrada com o número: " + cartao.getContaCorrente().getNumero());
        }

        contaCorrente.adicionaCartao(cartao);

        return cartaoRepository.save(cartao);

    }

    public Collection<Cartao> listaCartoes() {
        return cartaoRepository.findAll();
    }

    public ArrayList<Cartao> listaCartoesConta(String numero) {
        ContaCorrente contaCorrente = contaCorrenteRepository.findByNumero(numero);
        return contaCorrente.getCartoes();
    }

    public void cancelaCartao(String numeroCartao) {
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        assert cartao != null;
        cartao.cancelaCartao();
    }

    public boolean verificaCartaoAtivo(String numeroCartao) {
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
        assert cartao != null;
        return cartao.getStatus().equals("ATIVO");
    }

}
