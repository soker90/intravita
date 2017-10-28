package com.mensubiqua.intravita.auxiliar;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private static String userName = "designesi.uvly@gmail.com";
	private static String password = "superpasswordsegura";

	private static Session getSession() throws SQLException{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		return session;
	}
	public void sendMail(String email, String titulo, String contenido) throws Exception{
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject(titulo);
			message.setText(contenido);
			Transport.send(message);


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