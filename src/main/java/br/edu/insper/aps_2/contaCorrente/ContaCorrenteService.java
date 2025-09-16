package br.edu.insper.aps_2.contaCorrente;

import br.edu.insper.aps_2.cliente.Cliente;
import br.edu.insper.aps_2.cliente.ClienteService;
import br.edu.insper.aps_2.movimentacao.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ContaCorrenteService {

    @Autowired
    ClienteService clienteService;

    private final HashMap<String, ContaCorrente> contas = new HashMap<>();

    public ContaCorrente cadastraConta(String cpf, ContaCorrente contaCorrente) {
        Cliente cliente = clienteService.buscaCliente(cpf);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com o CPF: " + cpf);
        }

        contaCorrente.setCliente(cliente);
        cliente.setContaCorrente(contaCorrente);

        contas.put(contaCorrente.getNumero(), contaCorrente);

        return contaCorrente;
    }


    public Collection<ContaCorrente> listaContas() {
        return contas.values();
    }

    public ContaCorrente buscaConta(String numero) {
        return contas.get(numero);
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
        ContaCorrente conta = contas.get(numero);

        return conta.getMovimentacoes();
    }

}
