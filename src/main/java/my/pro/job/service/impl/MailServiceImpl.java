package my.pro.job.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import my.pro.job.dto.MailDTO;
import my.pro.job.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	@Override
	@Async
	public void sendMail(MailDTO mail) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
		Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process(mail.getTemplate(), context);
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(html, true);
        helper.addAttachment(mail.getImg(), new ClassPathResource("static/"+mail.getImg()));
		javaMailSender.send(message);		

	}

}
