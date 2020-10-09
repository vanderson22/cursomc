package br.com.casa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Pedido;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {

		Optional<Pedido> optional = repo.findById(id);

		return 
				optional.orElseThrow(
				() -> new ObjectNotFoundException("Pedido não encontrado  - identificador :[" + id + "]")
				);

	}

}
