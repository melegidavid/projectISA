package ftn.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioFlight;
import ftn.isa.model.User;

@Service
public class NotificationService {

	private JavaMailSender mailSender;
	
	@Autowired
	public NotificationService(JavaMailSender jms) {
		this.mailSender = jms;
	}
	
	public void sendNotification(User user) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("duleda.isa@gmail.com");
		mail.setSubject("Account verification for ProjectISA web site");
		mail.setText("You have been successfully registered to ProjectISA web site. Please go to the following link in order to verify your account: http://localhost:4200/auth/activate/" + user.getId());
		
		mailSender.send(mail);
		
	}
	
	public void sendInvite(User user1, User user2, AvioFlight flight) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user2.getEmail());
		mail.setFrom("duleda.isa@gmail.com");
		mail.setSubject("Invite for a vacation");
		mail.setText("You are invited for a vacation in" + flight.getEndLocation().getCity() + "," +flight.getEndLocation().getCountry() + "by: " + user1.getName() + " " + user1.getLastName() + "./n Check invites on your profile after: http://localhost:4200/auth/login");
	
		mailSender.send(mail);
	}
	
}
