package br.edu.insper.aps_2.cartao.dto;

import br.edu.insper.aps_2.cartao.model.Cartao;
import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.cliente.model.Cliente;

import java.time.LocalDate;

public record ResponseCartaoDTO(String numeroCartao, String tipo,
                                LocalDate validade, String status) {

    public static ResponseCartaoDTO toDto(Cartao cartao) {
        return new ResponseCartaoDTO(
                cartao.getNumeroCartao(),
                cartao.getTipo(),
                cartao.getValidade(),
                cartao.getStatus()
        );
    }

}
