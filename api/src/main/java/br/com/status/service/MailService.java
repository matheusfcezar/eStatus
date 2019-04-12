package br.com.status.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.status.dto.Email;

@Service
public class MailService {
	
	private final JavaMailSender emailSender;

	@Autowired
	public MailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Async
	public void enviarAssincrono(Email email) throws MessagingException {
		enviar(email);
	}

	public boolean enviar(Email email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(InternetAddress.parse(email.getEmailDestinatario()));
		helper.setSubject(email.getAssunto());
		helper.setText(email.getCorpoEmail());

		try {
			emailSender.send(message);

			return true;
		} catch (MailException e) {
			throw e;
		}
	}

	public void validar(Email email) {

	}

}
