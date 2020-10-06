package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
