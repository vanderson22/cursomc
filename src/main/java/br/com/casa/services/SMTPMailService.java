package br.com.casa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPMailService extends AbstractMailService {

	@Autowired
	private MailSender mail;

	private static final Logger log = LoggerFactory.getLogger(SMTPMailService.class);

	/**
	 * possui todos os parâmetros carregados do arquivo de parâmetros
	 * 
	 */
	@Override
	public void sendMail(SimpleMailMessage smp) {
		log.info("Enviando email ...");
		mail.send(smp);
		log.info("Finalizado envio do email");
	}

}
