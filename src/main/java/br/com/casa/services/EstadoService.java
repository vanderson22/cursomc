package br.com.casa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Estado;
import br.com.casa.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public List<Estado> buscarTodos() {

		return repo.findByOrderByNome();

	}

}
