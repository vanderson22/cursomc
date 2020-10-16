package br.com.casa.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.casa.dominio.Pedido;

public abstract class AbstractMailService implements EmailService {

	@Value(value = "${default.sender}")
	private String sender;

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
