package br.com.casa.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.casa.config.util.DevInstantiateDB;
import br.com.casa.services.EmailService;
import br.com.casa.services.MockMailService;

/**
 * Vai realizar a configuração com o profile test
 */
@Configuration
@Profile(value = "dev")
public class DevConfiguration {

	@Autowired
	private DevInstantiateDB instant;

	// recupera a propertie do arquivo indicado no profile
	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String propriedade;

	// Apenas para fins de teste criar um bean mock de email service
	@Bean
	public EmailService getEmailService() {

		return new MockMailService();
	}

	@Bean
	public boolean instantiateDEVConfiguration() throws ParseException {
		System.out.println("Instanciando configuração de dev...[" + propriedade + "]");
		if (propriedade.equals("create"))
			return instant.instantiateDEVConfiguration();
		return false;
	}

}
