package br.edu.insper.aps_2.cartao.service;

import br.edu.insper.aps_2.cartao.dto.CreateCartaoDTO;
import br.edu.insper.aps_2.cartao.dto.ResponseCartaoDTO;
import br.edu.insper.aps_2.cartao.model.Cartao;
import br.edu.insper.aps_2.cartao.repository.CartaoRepository;
import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.contaCorrente.model.ContaCorrente;
import br.edu.insper.aps_2.contaCorrente.repository.ContaCorrenteRepository;
import br.edu.insper.aps_2.contaCorrente.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    ContaCorrenteService contaCorrenteService;

    @Autowired
    private ContaCorrenteRepository  contaCorrenteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public ResponseCartaoDTO emiteCartao(CreateCartaoDTO createCartaoDTO) {

        Cartao cartao = new Cartao();

        cartao.setNumeroCartao(createCartaoDTO.numeroCartao());
        cartao.setTipo(createCartaoDTO.tipo());
        cartao.setStatus(createCartaoDTO.status());
        cartao.setValidade(createCartaoDTO.validade());

        cartaoRepository.save(cartao);

        return ResponseCartaoDTO.toDto(cartao);
    }

    public List<ResponseCartaoDTO> listaCartoes() {
        return cartaoRepository.
                findAll()
                .stream()
                .map(ResponseCartaoDTO::toDto)
                .toList();
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
