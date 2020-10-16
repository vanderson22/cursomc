package br.com.casa.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.casa.config.util.DevInstantiateDB;
import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Endereco;
import br.com.casa.dominio.Estado;
import br.com.casa.dominio.ItemPedido;
import br.com.casa.dominio.Pagamento;
import br.com.casa.dominio.PagamentoBoleto;
import br.com.casa.dominio.PagamentoCartao;
import br.com.casa.dominio.Pedido;
import br.com.casa.dominio.Produto;
import br.com.casa.dominio.enums.EstadoPagamento;
import br.com.casa.dominio.enums.TipoCliente;
import br.com.casa.repositories.CategoriaRepository;
import br.com.casa.repositories.CidadeRepository;
import br.com.casa.repositories.ClienteRepository;
import br.com.casa.repositories.EnderecoRepository;
import br.com.casa.repositories.EstadoRepository;
import br.com.casa.repositories.ItemRepository;
import br.com.casa.repositories.PagamentoRepository;
import br.com.casa.repositories.PedidoRepository;
import br.com.casa.repositories.ProdutoRepository;
import br.com.casa.services.EmailService;
import br.com.casa.services.MockMailService;
import br.com.casa.services.SMTPMailService;

/**
 * Vai realizar a configuração com o profile test
 */
@Configuration
@Profile(value = "test")
public class TestConfiguration {
	@Autowired
	private DevInstantiateDB instant;

	// recupera a propertie do arquivo indicado no profile
	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String propriedade;

	@Bean
	public EmailService getEmailService() {
		return new MockMailService();
	}

	@Bean
	public boolean instantiateTSTConfiguration() throws ParseException {
		if (propriedade.equals("create"))
			instant.instantiateDEVConfiguration();
		return false;
	}

}
