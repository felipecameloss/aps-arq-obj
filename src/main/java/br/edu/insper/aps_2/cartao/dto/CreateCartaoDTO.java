package br.edu.insper.aps_2.cartao.dto;

import java.time.LocalDate;

public record CreateCartaoDTO(String numeroCartao, String tipo,
                              LocalDate validade, String status) {
}
