package com.mensubiqua.intravita.auxiliar;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private static String userName = "intravita2017@gmail.com";
	private static String password = "ulises2017";

	private static Session getSession() throws SQLException {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.user", userName);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.trust", "*");

		Session session = Session.getDefaultInstance(props);
		return session;
	}

	public void sendMail(String email, String titulo, String contenido) throws Exception {
		try {
			Session sesion = getSession();
			Message message = new MimeMessage(sesion);
			message.setFrom(new InternetAddress(userName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(titulo);
			message.setText(contenido);
			
			Transport t = sesion.getTransport("smtp");
			t.connect(userName, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean esEmailValido(String email) {
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			return false;
		}
		return true;
	}
}
