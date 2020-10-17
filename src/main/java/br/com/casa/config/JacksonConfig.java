package br.com.casa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casa.dominio.PagamentoBoleto;
import br.com.casa.dominio.PagamentoCartao;

/**
 * Vai fazer o mapeamento dos @types pagamento com cartao e boleto. Pagamento
 * com cartão OU boleto serão instanciados.
 */
@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {

		// Informado 2 subtipos ao jackson registar pagamento com boleto e cartão
		return new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoBoleto.class);
				objectMapper.registerSubtypes(PagamentoCartao.class);
				super.configure(objectMapper);
			}

		};

	}

}
