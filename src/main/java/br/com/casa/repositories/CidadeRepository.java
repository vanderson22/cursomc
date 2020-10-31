package br.com.casa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	List<Cidade> findByEstado(Estado estado);

}
