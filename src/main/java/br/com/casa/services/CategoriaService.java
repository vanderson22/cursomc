package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.exceptions.DataIntegridadeException;
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

	public Categoria update(Categoria categoria) {

		return repo.save(buscar(categoria.getId()));
	}

	public void deletar(Integer id) {
		try {
			repo.delete(buscar(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegridadeException(
					"Não é possível excluir a categoria, pois ela possui produtos vinculados - Categoria id [" + id
							+ "]");
		}

	}

}
