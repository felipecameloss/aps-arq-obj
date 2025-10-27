package br.edu.insper.aps_2.cliente.repository;

import br.edu.insper.aps_2.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Cliente findByCpf(String cpf);

}
