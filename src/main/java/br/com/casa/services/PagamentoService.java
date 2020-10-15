package br.com.casa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Pagamento;
import br.com.casa.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repo;

	public void criar(Pagamento pagamento) {

		repo.save(pagamento);
	}

}
