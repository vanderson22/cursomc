package br.com.casa.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.casa.dominio.Pedido;

public abstract class AbstractMailService implements EmailService {

	@Value(value = "${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine template;

	@Autowired
	private JavaMailSender jms;

	public void sendOrderConfirmationHtmlEmail(Pedido obj) {

		MimeMessage sm;
		try {
			sm = prepareMailMimeMessage(obj);
			sendHtmlEmail(sm);
		} catch (MessagingException e) {
			// se falhar o e-mail HTML vai como texto.
			e.printStackTrace();
			sendConfirmationMail(obj);
		}

	}

	protected MimeMessage prepareMailMimeMessage(Pedido obj) throws MessagingException {
		MimeMessage msg = jms.createMimeMessage();

		MimeMessageHelper mmh = new MimeMessageHelper(msg, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Confirmação de pedido [" + obj.getId() + "]");
		mmh.setText(htmlFromTemplatePedido(obj));
		return msg;
	}

	/**
	 * Formatar string a partir de um context do thymeleaf
	 */
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		// seta as variaveis no templete e injeta o contexto no html
		context.setVariable("pedido", obj);
		return template.process("email/confirmacaoPedido", context);
	}

	@Override
	public void sendConfirmationMail(Pedido p) {

		SimpleMailMessage sm = prepareMail(p);

		sendMail(sm);
	}

	protected SimpleMailMessage prepareMail(Pedido p) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(p.getCliente().getEmail());
		message.setFrom(sender);
		message.setSubject("Pedido Confirmado [" + p.getId() + "]");
		message.setSentDate(new Date());

		// Mensagem
		message.setText(p.toString());

		return message;

	}

}
