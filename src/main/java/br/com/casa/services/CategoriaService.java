package br.com.casa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.DTO.CategoriaDTO;
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
		Categoria findCat = buscar(categoria.getId());
		findCat.setNome(categoria.getNome());
		return repo.save(findCat);
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

	/**
	 * 
	 * Busca paginada
	 */
	public Page<Categoria> buscaPaginada(Integer pagina, Integer quantidadeLinha, String orderBy, String direction) {

		PageRequest pr = PageRequest.of(pagina, quantidadeLinha, Direction.valueOf(direction), orderBy);

		return repo.findAll(pr);
	}

	public List<Categoria> buscarTodos() {

		return repo.findAll();
	}

	public Categoria fromDTO(CategoriaDTO dto) {

		return new Categoria(dto.getId(), dto.getNome());
	}

}
