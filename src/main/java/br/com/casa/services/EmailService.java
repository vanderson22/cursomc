package br.com.casa.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.casa.dominio.Pedido;

public interface EmailService {

	void sendConfirmationMail(Pedido p);

	void sendMail(SimpleMailMessage smp);
}
