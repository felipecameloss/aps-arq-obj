package br.edu.insper.aps_2.cartao;

import br.edu.insper.aps_2.autenticacao.Usuario;
import br.edu.insper.aps_2.autenticacao.UsuarioService;
import br.edu.insper.aps_2.contaCorrente.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Gatherer;

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
    @GetMapping("/conta/{numero}")
    public Collection<Cartao> listaCartoesConta(@PathVariable String numero) {
        return cartaoService.listaCartoesConta(numero);
    }

    // Cancela um cartão específico
    @PostMapping("/cancelar/{numeroCartao}")
    public void cancelaCartao(@RequestHeader(name = "token") String token,
                              @PathVariable String numeroCartao) {
        Usuario usuario = usuarioService.validarToken(token);
        cartaoService.cancelaCartao(numeroCartao);
    }

    // Verifica se um cartão está ativo
    @GetMapping("/ativo/{numeroCartao}")
    public boolean verificaCartaoAtivo(@PathVariable String numeroCartao) {
        return cartaoService.verificaCartaoAtivo(numeroCartao);
    }
}
