package br.com.casa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Cliente;
import br.com.casa.repositories.ClienteRepository;
import br.com.casa.services.security.DetalhesDeUsuario;

@Service
public class DetalhesUsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	private static final Logger log = LoggerFactory.getLogger(SMTPMailService.class);

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
// buscar por email
		Cliente cliente = repo.findByEmail(email);

		if (cliente == null)
			throw new UsernameNotFoundException("Cliente não foi encontrado - email [" + email + "]");

		log.debug("Cliente foi encontrado no banco de dados [" + cliente.getId() + "]");
		return new DetalhesDeUsuario(cliente.getId(), cliente.getSenha(), cliente.getEmail(), cliente.getPerfis());
	}

}
