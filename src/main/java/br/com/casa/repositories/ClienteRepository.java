package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
