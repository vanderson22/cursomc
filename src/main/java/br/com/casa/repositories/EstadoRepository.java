package br.com.casa.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional
	List<Estado> findByOrderByNome();

}
