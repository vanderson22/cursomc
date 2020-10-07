package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Cliente;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> optional = repo.findById(id);

		return 
				optional.orElseThrow(
				() -> new ObjectNotFoundException("Cliente não encontrado  - identificador :[" + id + "]")
				);

	}

}
