package br.edu.insper.aps_2.cartao;

import br.edu.insper.aps_2.autenticacao.Usuario;
import br.edu.insper.aps_2.autenticacao.UsuarioService;
import br.edu.insper.aps_2.contaCorrente.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private UsuarioService usuarioService;

    // Emite um novo cartão
    @PostMapping("/emitir")
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao emiteCartao(@RequestHeader(name = "token") String token,
                              @RequestBody Cartao cartao) {
        Usuario usuario = usuarioService.validarToken(token);
        return cartaoService.emiteCartao(cartao);
    }

    // Lista todos os cartões do sistema
    @GetMapping()
    public Collection<Cartao> listaCartoes() {
        return cartaoService.listaCartoes();
    }

    // Lista cartões de uma conta específica
    @GetMapping("/conta")
    public Collection<Cartao> listaCartoesConta(@RequestBody ContaCorrente conta) {
        return cartaoService.listaCartoesConta(conta);
    }

    // Cancela um cartão específico
    @PostMapping("/cancelar")
    public void cancelaCartao(@RequestHeader(name = "token") String token,
                              @RequestBody Cartao cartao) {
        Usuario usuario = usuarioService.validarToken(token);
        cartaoService.cancelaCartao(cartao);
    }

    // Verifica se um cartão está ativo
    @GetMapping("/ativo")
    public boolean verificaCartaoAtivo(@RequestBody Cartao cartao) {
        return cartaoService.verificaCartaoAtivo(cartao);
    }
}
