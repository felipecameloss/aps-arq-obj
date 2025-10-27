package br.edu.insper.aps_2.contaCorrente.dto;

import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.cliente.model.Cliente;
import br.edu.insper.aps_2.contaCorrente.model.ContaCorrente;

public record ResponseContaDTO(String agencia, String numero,
                               double saldo, double limite) {

    public static ResponseContaDTO toDto(ContaCorrente conta) {
        return new ResponseContaDTO(
                conta.getAgencia(),
                conta.getNumero(),
                conta.getSaldo(),
                conta.getLimite()
        );
    }

}
