package br.com.casa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Endereco;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco buscarPorId(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi possivel achar endereço - id [" + id + "]"));
	}

}
