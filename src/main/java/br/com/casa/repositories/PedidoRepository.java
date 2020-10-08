package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
