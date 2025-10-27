package br.edu.insper.aps_2.contaCorrente.repository;

import br.edu.insper.aps_2.contaCorrente.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {

    public ContaCorrente findByNumero(String numero);

}
