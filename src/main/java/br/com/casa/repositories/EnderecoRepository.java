package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
