package test.mails;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendSimpleEmails {
	
	public static void sendEmail() throws EmailException {
		
		Email email = new SimpleEmail();
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);  //465
		email.setAuthenticator(new DefaultAuthenticator("acekuazuki.automation@gmail.com", "pwd"));
		email.setSSLOnConnect(true);
		email.setStartTLSRequired(true);
		email.setFrom("acekuazuki.automation@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("sourabhsahil67@gmail.com");
		email.send();
		

}
}
