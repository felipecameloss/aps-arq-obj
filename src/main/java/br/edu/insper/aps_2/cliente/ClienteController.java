package br.edu.insper.aps_2.cliente;

import br.edu.insper.aps_2.autenticacao.Usuario;
import br.edu.insper.aps_2.autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastraCliente(@RequestHeader(name = "token") String token, @RequestBody Cliente cliente) {

        Usuario usuario = usuarioService.validarToken(token);

        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public Collection<Cliente> listaClientes() {
        return clienteService.listaClientes();
    }

    @PutMapping("/{cpf}")
    public Cliente editCliente(@RequestHeader(name = "token") String token, @PathVariable String cpf, @RequestBody Cliente novosDados) {

        Usuario usuario = usuarioService.validarToken(token);

        return clienteService.editaCliente(cpf, novosDados);
    }

    @GetMapping("/{cpf}")
    public Cliente buscaCliente(@PathVariable String cpf) {
        return clienteService.buscaCliente(cpf);
    }

}
