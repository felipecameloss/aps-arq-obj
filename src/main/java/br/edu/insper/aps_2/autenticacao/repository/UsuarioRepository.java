package br.edu.insper.aps_2.autenticacao.repository;

import br.edu.insper.aps_2.autenticacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String email);

}
