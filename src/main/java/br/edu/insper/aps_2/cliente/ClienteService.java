package br.edu.insper.aps_2.cliente;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ClienteService {

    private final HashMap<String, Cliente> clientes = new HashMap<>();

    public Cliente cadastrarCliente(Cliente cliente) {

        clientes.put(cliente.getCpf(), cliente);

        return cliente;

    }

    public Collection<Cliente> listaClientes() {
        return clientes.values();
    }

//    public Cliente editaCliente(String cpf) {
//
//        Cliente cliente = clientes.get(cpf);
//
//        if (cliente != null) {
//            if (cliente.getNome() != null) {
//                cliente.setNome(cliente.getNome());
//            }
//            if (cliente.getDataNascimento() != null) {
//                cliente.setDataNascimento(cliente.getDataNascimento());
//            }
//            if (cliente.getSalario() != null) {
//                cliente.setSalario(cliente.getSalario());
//            }
//        }
//        return cliente;
//
//    }

    public Cliente editaCliente(String cpf, Cliente novosDados) {
        Cliente cliente = clientes.get(cpf);

        if (cliente != null) {
            if (novosDados.getNome() != null) {
                cliente.setNome(novosDados.getNome());
            }
            if (novosDados.getDataNascimento() != null) {
                cliente.setDataNascimento(novosDados.getDataNascimento());
            }
            if (novosDados.getSalario() != null) {
                cliente.setSalario(novosDados.getSalario());
            }
        }
        return cliente;
    }

    public Cliente buscaCliente(String cpf) {
        return clientes.get(cpf);
    }

}
