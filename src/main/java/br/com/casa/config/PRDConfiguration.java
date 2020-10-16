package br.com.casa.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.casa.config.util.DevInstantiateDB;
import br.com.casa.services.EmailService;
import br.com.casa.services.PedidoService;
import br.com.casa.services.SMTPMailService;

/**
 * Vai realizar a configuração com o profile test
 */
@Configuration
@Profile(value = "prod")
public class PRDConfiguration {
	private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private DevInstantiateDB instant;

	// recupera a propertie do arquivo indicado no profile
	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String propriedade;

	@Bean
	public EmailService getEmailService() {
		log.info("Criando bean de e-mail");
		return new SMTPMailService();
	}

	@Bean
	public boolean instantiatePRDConfiguration() throws ParseException {
		if (propriedade.equals("create"))
			instant.instantiateDEVConfiguration();
		return false;
	}

}
