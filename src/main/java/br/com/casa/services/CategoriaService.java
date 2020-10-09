package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> optional = repo.findById(id);

		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Categoria não encontrada  - identificador :[" + id + "]"));

	}

	public Categoria criar(Categoria categoria) {
		return repo.save(categoria);

	}

}
