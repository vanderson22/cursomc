package br.com.casa.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import br.com.casa.dominio.Pedido;

public class MockMailService extends AbstractMailService {

	private static final Logger log = LoggerFactory.getLogger(MockMailService.class);

	@Override
	public void sendMail(SimpleMailMessage smp) {
		log.info("Starting mail ...");
		log.info(smp.toString());
		log.info("Finished mail.");
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		
	}

}
