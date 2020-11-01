package br.com.casa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

//	@Query("Select cidade from Cidade cidade where cidade.estado.id = :estadoId ORDER BY cidade.nome")
//	List<Cidade> findByEstado(@Param(estadoId) Integer estadoId);
	@Transactional(readOnly = true)
	List<Cidade> findByEstado(Estado estado);

}
