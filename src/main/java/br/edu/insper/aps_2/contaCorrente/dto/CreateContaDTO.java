package br.edu.insper.aps_2.contaCorrente.dto;

public record CreateContaDTO(String agencia, String numero,
                             double saldo, double limite, String cpf, String numeroCartao) {
}
