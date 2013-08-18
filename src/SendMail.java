import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private static String ip;
	private static boolean send = false;

	public static void main(String[] args) {

		final String username = "xyz@gmail.com";
		final String password = "***";

		if (username.equals("xyz@gmail.com")) {
			System.out
					.println("You need to provide correct Email-ID & Password. \n\nAlso Set the full path to your program related files in FileInput.java & FileOutput.java");
			System.exit(0);
		}

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(username));
			message.setSubject("Server IP Address");

			System.out.println("Settings ready. Check Start");

			//
			String str[] = { "http://checkip.amazonaws.com",
					"http://icanhazip.com/", "http://ifconfig.me/ip" };
			try {
				ip = IPFinder.getIp(str[0]);
				message.setText(ip + " - from checkip.amazonaws.com \n"
						+ IPFinder.getIp(str[1]) + " - from icanhazip.com/ \n"
						+ IPFinder.getIp(str[2]) + " - from ifconfig.me/ip \n");

				System.out.println("IP found.");

				System.out.println(FileInput.readFile());
				if (FileInput.readFile().equals(ip) == true) {
					send = false;
					System.out.println("Match found");
				} else {
					send = true;
					FileOutput.writeFile(ip);
					System.out.println("File written.");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				send = true;
				message.setText("No Ip Found - " + " \n\n " + e.toString()
						+ " \n\n " + e.getMessage());
				System.out.println("Error in check.");

				// Try Running the program again
				// Note this is optional
				try {
					Runtime.getRuntime().exec(
							"wscript Executables\\IPFinder.vbs");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			//

			if (send == true) {
				System.out.println("Send mail.");
				Transport.send(message);
			}
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}