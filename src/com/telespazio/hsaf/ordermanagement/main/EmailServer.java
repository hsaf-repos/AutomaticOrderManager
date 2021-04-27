package com.telespazio.hsaf.ordermanagement.main;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailServer {

	private static final String PROPERTY_FILE_PATH = "/UMARF/safclient/autoUMARF/serviceTools/orderManagerTool.properties";
	// Path of the log file where the porders are saved.


	private static final String MAILSERVER_KEY = "MAILSERVER";
	private static final String USERNAME_KEY = "USERNAME";
	private static final String PASSWORD_KEY = "PASSWORD";
	private static final String SENDER_KEY = "SENDER";
	private static final String COMMON_RECIPIENT_KEY = "COMMON_RECIPIENT";

	private String mailServer;
	private static String username;
	private static String password;
	private String sender;
	private ArrayList<InternetAddress> commonRecipient;
	

	public EmailServer() throws IOException {

		

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(PROPERTY_FILE_PATH);

			// Load properties file
			prop.load(input);

			// Read E-mail server configuration
			this.mailServer = prop.getProperty(MAILSERVER_KEY);
			EmailServer.username = prop.getProperty(USERNAME_KEY);
			EmailServer.password = prop.getProperty(PASSWORD_KEY);
			this.sender = prop.getProperty(SENDER_KEY);

			// Read E-mail addresses of common recipients
			String[] commonRecipientAddresses = prop.getProperty(COMMON_RECIPIENT_KEY).split(";");
			this.commonRecipient = new ArrayList<InternetAddress>();

			for (String address : commonRecipientAddresses) {
				// If condition to manage an empty ("empty" means "") key value
				if (address.contains("@")) {
					this.commonRecipient.add(new InternetAddress(address.trim()));
				}
			}

		} catch (Throwable ex) {
			
			String msg = "E-MAIL Server failure during initialization:" + ex.getMessage();
			AutomaticOrderManager.emailServer.sendmailFailureToAdmin(msg);
			AutomaticOrderManager.logger.warning(msg);

		} finally {

			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
	}

	public void sendmailHsaf(String msgStr, String userAddr) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", this.mailServer);
			props.put("mail.smtp.auth", "true");

			Authenticator authenticator = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EmailServer.username, EmailServer.password);
				}
			};

			Session session = Session.getInstance(props, authenticator);

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(this.sender));

			// Set "Bcc" recipients (from "COMMON_RECIPIENT_KEY")
			ArrayList<InternetAddress> addressesBcc = new ArrayList<InternetAddress>();
			addressesBcc.addAll(this.commonRecipient);
			InternetAddress[] BccArray = addressesBcc.toArray(new InternetAddress[addressesBcc.size()]);
			msg.setRecipients(Message.RecipientType.BCC, BccArray);

			// Set "To" recipient
			if (!userAddr.isEmpty() && userAddr.contains("@")) {
				ArrayList<InternetAddress> addressesTo = new ArrayList<InternetAddress>();
				addressesTo.add(new InternetAddress(userAddr));
				InternetAddress[] toArray = addressesTo.toArray(new InternetAddress[addressesTo.size()]);
				msg.setRecipients(Message.RecipientType.TO, toArray);
			}

			msg.setSubject("H-SAF Order Status notification");
			msg.setContent(msgStr, "text/plain");

			Transport.send(msg);

		} catch (Throwable ex) {
			System.out.println("E-MAIL Server failure sending email:");
			System.out.println("Main Recipient <" + userAddr + ">");
			System.out.println("Message:" + msgStr);
			ex.printStackTrace();
		}
	}
	
	public void sendmailFailureToAdmin(String msgStr) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", this.mailServer);
			props.put("mail.smtp.auth", "true");

			Authenticator authenticator = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(EmailServer.username, EmailServer.password);
				}
			};

			Session session = Session.getInstance(props, authenticator);

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(this.sender));

			// Set "Bcc" recipients (from "COMMON_RECIPIENT_KEY")
			ArrayList<InternetAddress> addressesBcc = new ArrayList<InternetAddress>();
			addressesBcc.addAll(this.commonRecipient);
			InternetAddress[] BccArray = addressesBcc.toArray(new InternetAddress[addressesBcc.size()]);
			msg.setRecipients(Message.RecipientType.BCC, BccArray);

			// Set "To" recipient
			
			ArrayList<InternetAddress> addressesTo = new ArrayList<InternetAddress>();
			addressesTo.add(new InternetAddress("gabriele.gottardo@telespazio.com"));
			InternetAddress[] toArray = addressesTo.toArray(new InternetAddress[addressesTo.size()]);
			msg.setRecipients(Message.RecipientType.TO, toArray);
			

			msg.setSubject("H-SAF Orders Failure");
			msg.setContent(msgStr, "text/plain");

			Transport.send(msg);

		} catch (Throwable ex) {
			System.out.println("E-MAIL Server failure sending email:");
			System.out.println("Message:" + msgStr);
			ex.printStackTrace();
		}
	}

}
