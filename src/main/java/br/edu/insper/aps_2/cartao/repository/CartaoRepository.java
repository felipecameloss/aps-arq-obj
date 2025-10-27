package br.edu.insper.aps_2.cartao.repository;

import br.edu.insper.aps_2.cartao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    public Cartao findByNumeroCartao(String numero);

}
