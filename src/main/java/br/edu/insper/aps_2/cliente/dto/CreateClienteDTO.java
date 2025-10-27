package br.edu.insper.aps_2.cliente.dto;

public record CreateClienteDTO(String nome, String cpf,
                               String dataNascimento, String salario) {
}
