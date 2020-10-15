package br.com.casa.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.casa.config.util.DevInstantiateDB;

/**
 * Vai realizar a configuração com o profile test
 */
@Configuration
@Profile(value = "prod")
public class PRDConfiguration {

	@Autowired
	private DevInstantiateDB instant;

	// recupera a propertie do arquivo indicado no profile
	@Value("spring.jpa.hibernate.ddl-auto")
	private String propriedade;

	@Bean
	public boolean instantiateTSTConfiguration() throws ParseException {
//		if (propriedade.equals("create"))
			return  true ; //  instant.instantiateDEVConfiguration();
//		return false;
	}

}
