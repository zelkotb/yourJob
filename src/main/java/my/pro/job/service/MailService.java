package my.pro.job.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import my.pro.job.dto.MailDTO;

@Component
public interface MailService {

	public void sendMail(MailDTO mail) throws MessagingException;
}
