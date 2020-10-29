package br.com.casa.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	/**
	 *  Consulta Paginada pedidos por cliente
	 * 
	 * **/
	@Transactional
	Page<Pedido>findByCliente(Cliente cliente, Pageable pageRequest);
}
