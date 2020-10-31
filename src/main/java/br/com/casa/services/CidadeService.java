package br.com.casa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Estado;
import br.com.casa.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> buscarTodos() {

		return repo.findAll();
	}

	public List<Cidade> buscarCidadePorEstado(Estado estado) {

		return repo.findByEstado(estado);
	}

}
