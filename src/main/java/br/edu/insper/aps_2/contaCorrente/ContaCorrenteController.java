package br.edu.insper.aps_2.contaCorrente;

import br.edu.insper.aps_2.autenticacao.Usuario;
import br.edu.insper.aps_2.autenticacao.UsuarioService;
import br.edu.insper.aps_2.movimentacao.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/contacorrente")
public class ContaCorrenteController {

    @Autowired
    ContaCorrenteService contaCorrenteService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaCorrente cadastrarConta(@RequestHeader(name = "token") String token,
                                        @RequestParam String cpf,
                                        @RequestBody ContaCorrente conta) {
        Usuario usuario = usuarioService.validarToken(token);
        return contaCorrenteService.cadastraConta(cpf, conta);
    }

    // Lista todas as contas
    @GetMapping
    public Collection<ContaCorrente> listaContas() {
        return contaCorrenteService.listaContas();
    }

    // Realiza saque
    @PostMapping("/saque")
    public void realizarSaque(@RequestHeader(name = "token") String token,
                              @RequestParam String cpf,
                              @RequestParam Double valor) {
        Usuario usuario = usuarioService.validarToken(token);
        contaCorrenteService.realizarSaque(cpf, valor);
    }

    // Realiza depósito
    @PostMapping("/deposito")
    public void realizarDeposito(@RequestHeader(name = "token") String token,
                                 @RequestParam String cpf,
                                 @RequestParam Double valor) {
        Usuario usuario = usuarioService.validarToken(token);
        contaCorrenteService.realizarDeposito(cpf, valor);
    }

    // Lista movimentações de uma conta específica
    @GetMapping("/{numero}/movimentacoes")
    public Collection<Movimentacao> listaMovimentacoes(@PathVariable String numero) {
        return contaCorrenteService.listaMovimentacoes(numero);
    }
}
