package br.com.casa.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.ItemPedido;
import br.com.casa.repositories.ItemRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemRepository repo;

	public void criar(Set<ItemPedido> itens) {

		repo.saveAll(itens);
	}

}
