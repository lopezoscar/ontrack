package com.sappe.ontrack.dao.springbeans.impl;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.dao.springbeans.interfaces.MailManager;
import com.sappe.ontrack.model.notifications.PriorityEnum;

public class MailBean implements MailManager{

	/** Static variable representing a high priority message. */
	public final static String HIGH_PRIORITY = "1";
	/** Static variable representing a normal priority message. */
	public final static String NORMAL_PRIORITY = "3";
	/** Static variable representing a low priority message. */
	public final static String LOW_PRIORITY = "5";

	private Properties properties;

	/**
	 * Class constructor
	 */
	public MailBean() {
		if (properties == null) {
			properties = new Properties();
		}
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("username", "lopezoscar.job@gmail.com");
		properties.put("password", "javaDeveloper1");
	}

	/**
	 * Sets the notificator properties
	 * 
	 * @param props
	 *            HashMap with the corresponding notificator properties
	 */
	public void setNotificatorProperties(String host) {
	
		
	}

	/**
	 * Sends a single email to a determined user
	 * 
	 * @param from -
	 *            email from sending the email
	 * @param to -
	 *            email to send the email
	 * @param cc -
	 *            email to send cc the email
	 * @param subject -
	 *            email subject
	 * @param body -
	 *            email body.
	 * @return boolean indicating the result of sending the email.
	 * @throws NotificatorException
	 */
	public boolean sendEmail(String from, String to, String cc, String subject, String body,PriorityEnum priority) throws NotificatorException {
		// Here, no Authenticator argument is used (it is null).
		// Authenticators are used to prompt the user for user
		// name and password.
		if (properties == null)
			throw new NotificatorException("No properties set for this action...");
		
		Session session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
					}
				  });
		
		
//		Session session = Session.getDefaultInstance(properties, null);
		MimeMessage message = new MimeMessage(session);
		try {
			// the "from" address may be set in code, or set in the
			// config file under "mail.from" ; here, the latter style is used
			// message.setFrom( new InternetAddress(from) );
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			if (cc != null) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			}
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(body);
			if(priority!=null){
				message.setHeader("X-Priority", priority.getNumber());
			}

			Transport.send(message);
			System.out.println("An email was sent to:" + to);
			return true;
		} catch (MessagingException ex) {
			System.out.println("Cannot send email. " + ex);
			return false;
		}
	}

	/**
	 * 
	 * @param from
	 * @param toAddresses
	 * @param ccAddresses
	 * @param subject
	 * @param body
	 * @return
	 * @throws NotificatorException
	 */
	public boolean sendEmail(String from, List toAddresses, List ccAddresses, String subject, String body,PriorityEnum priority) throws NotificatorException {
		// Here, no Authenticator argument is used (it is null).
		// Authenticators are used to prompt the user for user
		// name and password.
		if (properties == null)
			throw new NotificatorException("No properties set for this action...");
//		Session session = Session.getDefaultInstance(properties, null);
		Session session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
					}
				  });
		MimeMessage message = new MimeMessage(session);
		try {
			// the "from" address may be set in code, or set in the
			// config file under "mail.from" ; here, the latter style is used
			// message.setFrom( new InternetAddress(from) );
			for (int idxto = 0 ; idxto < toAddresses.size() ; idxto ++){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) toAddresses.get(idxto)));
			}
			if (ccAddresses != null) {
				for (int idxcc = 0 ; idxcc < ccAddresses.size() ; idxcc ++){
					message.addRecipient(Message.RecipientType.CC, new InternetAddress((String) ccAddresses.get(idxcc)));
				}

			}
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(body);
			if(priority!=null){
				System.out.println("Priority:"+priority);
				message.setHeader("X-Priority", priority.getNumber());
			}
			Transport.send(message);
			return true;
		} catch (MessagingException ex) {
			System.out.println("Cannot send email. " + ex);
			return false;
		}
	}



	/**
	 * Sends a single email to a determined user
	 * 
	 * @param from -
	 *            email from sending the email
	 * @param to -
	 *            email to send the email
	 * @param subject -
	 *            email subject
	 * @param body -
	 *            email body.
	 * @return boolean indicating the result of sending the email.
	 * @throws NotificatorException
	 */
	public boolean sendEmail(String from, String to, String subject, String body,PriorityEnum priority) throws NotificatorException {
		return sendEmail(from, to, null, subject, body,priority);
	}

	public boolean sendEmailWithAttachment(String from, String to, String cc, String subject, String body, String filename) throws NotificatorException {
		// Here, no Authenticator argument is used (it is null).
		// Authenticators are used to prompt the user for user
		// name and password.
		if (properties == null)
			throw new NotificatorException("No properties set for this action...");
//		Session session = Session.getDefaultInstance(properties, null);
		
		Session session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
					}
				  });
		MimeMessage message = new MimeMessage(session);
		try {
			// the "from" address may be set in code, or set in the
			// config file under "mail.from" ; here, the latter style is used
			// message.setFrom( new InternetAddress(from) );
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			if (cc != null) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			}


			System.out.println("Action send mail...");
			System.out.println("Sending mail from:" + from);

			// Set from whom the message is sent
			message.setFrom(new InternetAddress(from));

			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("An email was sent to:" + to);
			return true;
		} catch (MessagingException ex) {
			System.out.println("Cannot send email. " + ex);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
