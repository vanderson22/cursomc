package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
