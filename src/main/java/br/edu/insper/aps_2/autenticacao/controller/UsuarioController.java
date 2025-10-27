package br.edu.insper.aps_2.autenticacao.controller;

import br.edu.insper.aps_2.autenticacao.model.Usuario;
import br.edu.insper.aps_2.autenticacao.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "API de gerenciamento de usuários", description = "API de gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Cadastra usuário",
            description = "Cria usuário e armazena as informações no banco de dados"
    )
    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @Operation(
            summary = "Lista usuários",
            description = "Lista todos os usuários armazenados no banco de dados"
    )
    @GetMapping
    public Collection<Usuario> listaUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @Operation(
            summary = "Login de usuário",
            description = "Faz o login de um usário e retorna um token para o usuário se manter logado"
    )
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return usuarioService.login(usuario);
    }


}
