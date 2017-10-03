package email;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail_4 {
    public static void main(String[] args) {

	String host = "smtp.gmail.com";
	String port = "465";
	String from = "i40.Penev@gmail.com";
	String to = "i40.Penev@gmail.com";

	String pass = enterPass();

	Properties props = new Properties();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.user", from);
	props.put("mail.smtp.password", pass);
	props.put("mail.smtp.port", port);
	props.put("mail.smtp.auth", "true");
	props.put("mail.transport.protocol", "smtps");
	props.put("mail.password", pass);
	props.put("mail.smtp.debug", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.socketFactory.port", port);
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.socketFactory.fallback", "false");

	Session session = Session.getDefaultInstance(props);

	String subject = "java mail";
	Message msg = new MimeMessage(session);
	try {
	    msg.setFrom(new InternetAddress(from));
	    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    msg.setSubject(subject);
	    msg.setText("Mails are so secured, that it is hard to send an email via java");

	    Transport transport = session.getTransport("smtps");
	    transport.connect(host, from, pass);
	    transport.sendMessage(msg, msg.getAllRecipients());
	    transport.close();

	} catch (MessagingException e) {
	    e.printStackTrace();
	}
    }

    private static String enterPass() {
	String pass = null;
	try (Scanner scanner = new Scanner(System.in);) {
	    System.out.print("Enter pass: ");
	    pass = scanner.nextLine();
	}
	return pass;
    }
}
