package br.edu.insper.aps_2.cartao.controller;

import br.edu.insper.aps_2.autenticacao.model.Usuario;
import br.edu.insper.aps_2.autenticacao.service.UsuarioService;
import br.edu.insper.aps_2.cartao.dto.CreateCartaoDTO;
import br.edu.insper.aps_2.cartao.dto.ResponseCartaoDTO;
import br.edu.insper.aps_2.cartao.model.Cartao;
import br.edu.insper.aps_2.cartao.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartoes")
@Tag(name = "API de gerenciamento de cartões", description = "API de gerenciamento de cartões de banco")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private UsuarioService usuarioService;

    // Emite um novo cartão
    @Operation(
            summary = "Emissão de cartão",
            description = "Emite cartão e vincula à conta corrente do cliente"
    )
    @PostMapping("/emitir")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCartaoDTO emiteCartao(@RequestHeader(name = "token") String token,
                              @RequestBody CreateCartaoDTO cartao) {
        Usuario usuario = usuarioService.validarToken(token);
        return cartaoService.emiteCartao(cartao);
    }

    @Operation(
            summary = "Lista cartões",
            description = "Listagem de todos os cartões do banco de dados"
    )
    // Lista todos os cartões do sistema
    @GetMapping()
    public List<ResponseCartaoDTO> listaCartoes() {
        return cartaoService.listaCartoes();
    }

    // Lista cartões de uma conta específica
    @Operation(
            summary = "Lista cartões de uma conta específica",
            description = "Listagem de todos os cartões de uma conta corrente específica"
    )
    @GetMapping("/conta/{numero}")
    public ArrayList<Cartao> listaCartoesConta(@PathVariable String numero) {
        return cartaoService.listaCartoesConta(numero);
    }

    // Cancela um cartão específico
    @Operation(
            summary = "Cancela cartões",
            description = "Cancela o cartão"
    )
    @PostMapping("/cancelar/{numeroCartao}")
    public void cancelaCartao(@RequestHeader(name = "token") String token,
                              @PathVariable String numeroCartao) {
        Usuario usuario = usuarioService.validarToken(token);
        cartaoService.cancelaCartao(numeroCartao);
    }

    // Verifica se um cartão está ativo
    @Operation(
            summary = "Verifica se o cartão está ativo",
            description = "Retorna um valor booleano dependendo se o cartão está ativo ou não"
    )
    @GetMapping("/ativo/{numeroCartao}")
    public boolean verificaCartaoAtivo(@PathVariable String numeroCartao) {
        return cartaoService.verificaCartaoAtivo(numeroCartao);
    }
}
