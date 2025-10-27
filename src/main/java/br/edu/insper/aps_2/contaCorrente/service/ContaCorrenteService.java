package br.edu.insper.aps_2.contaCorrente.service;

import br.edu.insper.aps_2.cartao.model.Cartao;
import br.edu.insper.aps_2.cartao.repository.CartaoRepository;
import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.cliente.model.Cliente;
import br.edu.insper.aps_2.cliente.repository.ClienteRepository;
import br.edu.insper.aps_2.cliente.service.ClienteService;
import br.edu.insper.aps_2.contaCorrente.dto.CreateContaDTO;
import br.edu.insper.aps_2.contaCorrente.dto.ResponseContaDTO;
import br.edu.insper.aps_2.contaCorrente.model.ContaCorrente;
import br.edu.insper.aps_2.contaCorrente.repository.ContaCorrenteRepository;
import br.edu.insper.aps_2.movimentacao.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContaCorrenteService {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    public ResponseContaDTO cadastraConta(CreateContaDTO createContaDTO) {

        ContaCorrente conta = new ContaCorrente();

        conta.setAgencia(createContaDTO.agencia());
        conta.setNumero(createContaDTO.numero());
        conta.setLimite(createContaDTO.limite());
        conta.setSaldo(createContaDTO.saldo());

        Cliente cliente = clienteRepository.findByCpf(createContaDTO.cpf());

        conta.setCliente(cliente);

        Cartao cartao = cartaoRepository.findByNumeroCartao(createContaDTO.numeroCartao());

        conta.adicionaCartao(cartao);

        contaCorrenteRepository.save(conta);

        return ResponseContaDTO.toDto(conta);

    }


    public List<ResponseContaDTO> listaContas() {
        return contaCorrenteRepository
                .findAll()
                .stream()
                .map(ResponseContaDTO::toDto)
                .toList();
    }

    public ResponseContaDTO buscaConta(String numero) {
        ContaCorrente conta = contaCorrenteRepository.findByNumero(numero);
        return ResponseContaDTO.toDto(conta);
    }

    public void realizarSaque(String cpf, double valor) {

        Cliente cliente = clienteService.buscaCliente(cpf);
        ContaCorrente conta = cliente.getContaCorrente();

        conta.saque(valor);

    }

    public void realizarDeposito(String cpf, double valor) {

        Cliente cliente = clienteService.buscaCliente(cpf);
        ContaCorrente conta = cliente.getContaCorrente();

        conta.deposito(valor);

    }

    public Collection<Movimentacao> listaMovimentacoes(String numero) {
        ContaCorrente conta = contaCorrenteRepository.findByNumero(numero);

        return conta.getMovimentacoes();
    }

}
