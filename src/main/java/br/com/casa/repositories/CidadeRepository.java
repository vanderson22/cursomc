package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
