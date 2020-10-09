package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.ItemPedido;

public interface ItemRepository extends JpaRepository<ItemPedido, Integer> {

}
