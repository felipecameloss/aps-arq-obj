package br.edu.insper.aps_2.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscaClientePorId(Integer Id) {
        return  clienteRepository.findById(Id).orElse(null);
    }

    public Cliente buscaCliente(String cpf) {
        return  clienteRepository.findByCpf(cpf);
    }

    public Cliente cadastrarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    public Collection<Cliente> listaClientes() {

        return clienteRepository.findAll();

    }

    public Cliente editaCliente(String cpf, Cliente novosDados) {
        Cliente cliente = buscaCliente(cpf);

        if (novosDados.getNome() != null) {
            cliente.setNome(novosDados.getNome());
        }
        if (novosDados.getDataNascimento() != null) {
            cliente.setDataNascimento(novosDados.getDataNascimento());
        }
        if (novosDados.getSalario() != null) {
            cliente.setSalario(novosDados.getSalario());
        }

        return clienteRepository.save(cliente);
    }

}
