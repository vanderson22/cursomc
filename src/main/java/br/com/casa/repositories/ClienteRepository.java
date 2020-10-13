package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casa.dominio.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Encontra um cliente por e-mail
	 * 
	 */
	@Transactional(readOnly = true)
	public Cliente findByEmail(String email);

}
