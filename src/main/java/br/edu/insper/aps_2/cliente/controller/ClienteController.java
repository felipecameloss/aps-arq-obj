package br.edu.insper.aps_2.cliente.controller;

import br.edu.insper.aps_2.autenticacao.model.Usuario;
import br.edu.insper.aps_2.autenticacao.service.UsuarioService;
import br.edu.insper.aps_2.cliente.dto.CreateClienteDTO;
import br.edu.insper.aps_2.cliente.dto.EditClienteDTO;
import br.edu.insper.aps_2.cliente.dto.ResponseClienteDTO;
import br.edu.insper.aps_2.cliente.model.Cliente;
import br.edu.insper.aps_2.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "API de gerenciamento de clientes", description = "API de gerenciamento de clientes de banco")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Cadastro de cliente",
            description = "Cadastro de cliente no banco de dados"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseClienteDTO cadastraCliente(@RequestHeader(name = "token") String token, @RequestBody CreateClienteDTO cliente) {

        Usuario usuario = usuarioService.validarToken(token);

        return clienteService.cadastrarCliente(cliente);
    }

    @Operation(
            summary = "Lista clientes",
            description = "Lista todos os clientes armazenados no banco de dados"
    )
    @GetMapping
    public List<ResponseClienteDTO> listaClientes() {
        return clienteService.listaClientes();
    }

    @Operation(
            summary = "Editar clientes",
            description = "Edita as informações de clientes no banco de dados"
    )
    @PutMapping("/{cpf}")
    public ResponseClienteDTO editCliente(@RequestHeader(name = "token") String token, @PathVariable String cpf, @RequestBody EditClienteDTO novosDados) {

        Usuario usuario = usuarioService.validarToken(token);

        return clienteService.editaCliente(cpf, novosDados);
    }


    @Operation(
            summary = "Busca de clientes específico",
            description = "Busca cliente no banco de dados por meio do cpf"
    )
    @GetMapping("/{cpf}")
    public Cliente buscaCliente(@PathVariable String cpf) {
        return clienteService.buscaCliente(cpf);
    }

}
