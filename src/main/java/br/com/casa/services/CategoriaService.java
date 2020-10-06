package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Optional<Categoria> buscar(Integer id ) {
		
		return repo.findById(id);
		
	}

}
