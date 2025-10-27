package br.edu.insper.aps_2.cliente.service;

import br.edu.insper.aps_2.cliente.dto.CreateClienteDTO;
import br.edu.insper.aps_2.cliente.dto.EditClienteDTO;
import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.cliente.model.Cliente;
import br.edu.insper.aps_2.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseClienteDTO buscaClientePorId(Integer Id) {
        Cliente cliente = clienteRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseClienteDTO.toDto(cliente);
    }

    public Cliente buscaCliente(String cpf) {
        return  clienteRepository.findByCpf(cpf);
    }

    public ResponseClienteDTO cadastrarCliente(CreateClienteDTO createClienteDTO) {

        Cliente cliente = new Cliente();
        cliente.setNome(createClienteDTO.nome());
        cliente.setCpf(createClienteDTO.cpf());
        cliente.setDataNascimento(LocalDate.parse(createClienteDTO.dataNascimento()));
        cliente.setSalario(Float.valueOf(createClienteDTO.salario()));

        clienteRepository.save(cliente);

        return ResponseClienteDTO.toDto(cliente);

    }

    public List<ResponseClienteDTO> listaClientes() {

        return clienteRepository
                .findAll()
                .stream()
                .map(ResponseClienteDTO::toDto)
                .toList();

    }

    public ResponseClienteDTO editaCliente(String cpf, EditClienteDTO novosDados) {
        Cliente cliente = buscaCliente(cpf);

        if (novosDados.nome() != null) {
            cliente.setNome(novosDados.nome());
        }
        if (novosDados.dataNascimento() != null) {
            cliente.setDataNascimento(LocalDate.parse(novosDados.dataNascimento()));
        }
        if (novosDados.salario() != null) {
            cliente.setSalario(Float.valueOf(novosDados.salario()));
        }

        cliente = clienteRepository.save(cliente);

        return ResponseClienteDTO.toDto(cliente);

    }

}
