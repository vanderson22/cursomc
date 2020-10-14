package br.com.casa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Produto;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.CategoriaRepository;
import br.com.casa.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository catRepo;

	public Produto buscar(Integer id) throws ObjectNotFoundException {

		Optional<Produto> optional = repo.findById(id);

		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Produto não encontrado  - identificador :[" + id + "]"));

	}

	public Page<Produto> buscaPaginada(String nome, List<Integer> ids, Integer pagina, Integer quantidadeLinha,
			String orderBy, String direction) {

		PageRequest pr = PageRequest.of(pagina, quantidadeLinha, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);

		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pr);
	}

	public List<Produto> buscarTodos() {
		return repo.findAll();
	}
}
