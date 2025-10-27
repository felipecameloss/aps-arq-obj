package br.edu.insper.aps_2.cliente.dto;

import br.edu.insper.aps_2.cliente.model.Cliente;

import java.time.LocalDate;

public record ResponseClienteDTO(String nome, String cpf, LocalDate dataNascimento) {

    public static ResponseClienteDTO toDto(Cliente cliente) {
        return new ResponseClienteDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNascimento()
        );
    }

}
