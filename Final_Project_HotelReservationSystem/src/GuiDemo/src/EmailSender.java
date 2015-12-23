/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */
package GuiDemo.src;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


public class EmailSender 
{
	/**
	 * Accepts the Sender's email id, password, message, receiver's email id
	 * @param from sender's email id
	 * @param password password of the sender
	 * @param message the text message or the body of the message
	 * @param to the receiver's email id
	 * @return
	 */
	public static boolean SendMail(String from, String password, String message, String to[]){
		
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setFrom(new InternetAddress(from));
			
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for(int i = 0; i < to.length; i++){
				toAddress[i] = new InternetAddress(to[i]);
			}
			
			for(int i = 0; i < toAddress.length; i++){
				mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
			}
			mimeMessage.setSubject("Thanks for choosing our Hotel. Please check the Inforrmation furnished for your hotel accomodation.");
			mimeMessage.setText(message);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
