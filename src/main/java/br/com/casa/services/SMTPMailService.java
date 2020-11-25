package br.com.casa.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPMailService extends AbstractMailService {

	@Autowired // PEGA AUTOMATICAMENTE TODOS OS DADOS DE E-MAIL no properties
	private MailSender mail;

	@Autowired 
	private JavaMailSender javamail;

	private static final Logger log = LoggerFactory.getLogger(SMTPMailService.class);

	/**
	 * possui todos os parâmetros carregados do arquivo de parâmetros
	 * 
	 */
	@Override
	public void sendMail(SimpleMailMessage msg) {
		log.info("Enviando email ...");
		mail.send(msg);
		log.info("Finalizado envio do email");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Enviando email ...");
		javamail.send(msg);
		log.info("Finalizado envio do email");

	}

}
