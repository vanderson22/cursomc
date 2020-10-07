package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.repositories.CategoriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> optional = repo.findById(id);

		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id :[" + id + "]"));

	}

}
