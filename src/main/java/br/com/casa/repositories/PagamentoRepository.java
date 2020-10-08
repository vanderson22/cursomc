package br.com.casa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casa.dominio.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
