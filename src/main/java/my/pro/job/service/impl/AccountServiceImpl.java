package my.pro.job.service.impl;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.pro.job.dto.MailDTO;
import my.pro.job.entity.Account;
import my.pro.job.repository.AccountRepository;
import my.pro.job.service.AccountService;
import my.pro.job.service.MailService;
import my.pro.job.util.aop.audit.Auditable;

@Service
public class AccountServiceImpl implements AccountService{
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MailService mailServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	@Transactional
	@Auditable(action = "create à client")
	public Account saveAccount(Account a) {
		String password = a.getPassword();
		String encodedPassword = bcrypt.encode(a.getPassword());
		a.setPassword(encodedPassword);
		Account account = accountRepository.save(a);
		MailDTO mail = new MailDTO();
		mail.setFrom(this.from);
		mail.setTo(a.getEmail());
		mail.setSubject("YourJob Welcom message");
		mail.getProps().put("username", a.getUsername());
		mail.getProps().put("password", password);
		mail.getProps().put("sign", "YourJob Team");
		mail.getProps().put("location", "Casablanca");
		mail.setImg("welcom.png");
		try {
			mailServiceImpl.sendMail(mail);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(),e);
		}
		return account;
	}

	
	@Transactional
	@Auditable(action = "update a client")
	@Override
	public Account updateAccount(Long id, Account a) {
		a.setId(id);
		return accountRepository.save(a);
	}
	
	
}
