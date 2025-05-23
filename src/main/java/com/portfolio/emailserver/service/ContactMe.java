package com.portfolio.emailserver.service;

import com.portfolio.emailserver.config.AppConfig;
import com.portfolio.emailserver.model.ContactInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

public class ContactMe {
	private static final Logger logger = LogManager.getLogger(ContactMe.class);
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validateInfo(ContactInfo contactInfo) {
		if (Objects.isNull(contactInfo)) {
			return false;
		} else if (!(contactInfo.getName() != null && !contactInfo.getName().trim().isEmpty())) {
			return false;
		} else return contactInfo.getEmail() != null && !contactInfo.getEmail().trim().isEmpty() &&
				VALID_EMAIL_ADDRESS_REGEX.matcher(contactInfo.getEmail()).matches();
	}

	public static void sendEmail(ContactInfo contactInfo) {
		AppConfig appConfig = AppConfig.getInstance();

		final String email = appConfig.getSmtpEmail();
		final String password = appConfig.getSmtpPassword();

		String smtpPort = appConfig.getSmtpPort();
		if (Objects.isNull(smtpPort)) {
			smtpPort = "587";
		}

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", smtpPort);

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(email)
			);
			message.setSubject("New Contact from Portfolio");

			String content = String.format(
					"Name: %s%nEmail: %s%nMobile: %s%nMessage: %s",
					contactInfo.getName(),
					contactInfo.getEmail(),
					contactInfo.getMobile(),
					contactInfo.getMessage()
			);

			message.setText(content);
			Transport.send(message);

			logger.info("Email sent successfully. User name: {}, Email: {}", contactInfo.getName(), contactInfo.getEmail());
		} catch (MessagingException exception) {
			logger.error("Error when sending email: " + exception);
		}
	}
}
