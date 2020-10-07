package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
