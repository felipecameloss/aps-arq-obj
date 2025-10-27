package br.edu.insper.aps_2.contaCorrente.controller;

import br.edu.insper.aps_2.autenticacao.model.Usuario;
import br.edu.insper.aps_2.autenticacao.service.UsuarioService;
import br.edu.insper.aps_2.contaCorrente.dto.CreateContaDTO;
import br.edu.insper.aps_2.contaCorrente.dto.ResponseContaDTO;
import br.edu.insper.aps_2.contaCorrente.service.ContaCorrenteService;
import br.edu.insper.aps_2.movimentacao.Movimentacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/contacorrente")
@Tag(name = "API de Conta Corrente", description = "API de gerenciamento de conta corrente")
public class ContaCorrenteController {

    @Autowired
    ContaCorrenteService contaCorrenteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Cadastro uma conta corrente",
            description = "Crio e salvo no banco de dados uma conta corrente vinculada a um cliente," +
                    "a um array de cartões e a um array de movimentações"
    )
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseContaDTO cadastrarConta(@RequestHeader(name = "token") String token,
                                           @RequestBody CreateContaDTO conta) {
        Usuario usuario = usuarioService.validarToken(token);
        return contaCorrenteService.cadastraConta(conta);
    }

    // Lista todas as contas
    @Operation(
            summary = "Lista todas as contas",
            description = "Retorna uma lista com todas as contas salvas no banco de dados"
    )
    @GetMapping
    public List<ResponseContaDTO> listaContas() {
        return contaCorrenteService.listaContas();
    }

    // Realiza saque
    @Operation(
            summary = "Realiza saque",
            description = "Realiza um saque relacionado a um cartão de uma conta corrente," +
                    "além de verificar se o saque é possível de ser feito"
    )
    @PostMapping("/saque")
    public void realizarSaque(@RequestHeader(name = "token") String token,
                              @RequestParam String cpf,
                              @RequestParam Double valor) {
        Usuario usuario = usuarioService.validarToken(token);
        contaCorrenteService.realizarSaque(cpf, valor);
    }

    // Realiza depósito
    @Operation(
            summary = "Realiza depósito",
            description = "Realiza um depósito relacionado a um cartão de uma conta corrente," +
                    "além de verificar se o depósito é possível de ser feito"
    )
    @PostMapping("/deposito")
    public void realizarDeposito(@RequestHeader(name = "token") String token,
                                 @RequestParam String cpf,
                                 @RequestParam Double valor) {
        Usuario usuario = usuarioService.validarToken(token);
        contaCorrenteService.realizarDeposito(cpf, valor);
    }

    // Lista movimentações de uma conta específica
    @Operation(
            summary = "Lista movimentos de uma conta específica",
            description = "Retorna o array de movimentações de uma conta"
    )
    @GetMapping("/{numero}/movimentacoes")
    public Collection<Movimentacao> listaMovimentacoes(@PathVariable String numero) {
        return contaCorrenteService.listaMovimentacoes(numero);
    }
}
