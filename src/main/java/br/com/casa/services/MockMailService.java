package br.com.casa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractMailService {

	private static final Logger log = LoggerFactory.getLogger(MockMailService.class);

	@Override
	public void sendMail(SimpleMailMessage smp) {
		log.info("Starting mail ...");
		log.info(smp.toString());
		log.info("Finished mail.");
	}

}
