package my.pro.job.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.pro.job.dto.MailDTO;
import my.pro.job.entity.Account;
import my.pro.job.entity.ResetPassword;
import my.pro.job.repository.AccountRepository;
import my.pro.job.repository.ResetPasswordRepository;
import my.pro.job.service.AccountService;
import my.pro.job.service.MailService;
import my.pro.job.util.aop.audit.Auditable;
import my.pro.job.util.exception.CustomException;

/**
 *
 * @author Elkotb Zakaria
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Value("${spring.mail.username}")
	private String from;

	@Value("${spring.mail.sign}")
	private String sign;

	@Value("${spring.mail.location}")
	private String location;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ResetPasswordRepository resetPasswordRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	@Transactional
	@Auditable(action = "create a client")
	public Account saveAccount(Account a) throws MessagingException, CustomException {
		if ((this.accountRepository.findByEmail(a.getEmail()) == null)
				&& (this.accountRepository.findByUsername(a.getUsername()) == null)) {
			String password = a.getPassword();
			String encodedPassword = this.bcrypt.encode(a.getPassword());
			a.setPassword(encodedPassword);
			Account account = this.accountRepository.save(a);
			MailDTO mail = MailDTO.builder().from(this.from).to(a.getEmail()).subject("YourJob Welcom message")
					.template("welcomMail").img("welcom.png").build();
			Map<String, Object> props = new HashMap<>();
			props.put("username", a.getUsername());
			props.put("password", password);
			props.put("sign", this.sign);
			props.put("location", this.location);
			mail.setProps(props);
			this.mailService.sendMail(mail);
			return account;
		} else {
			throw new CustomException("email or username already existe");
		}

	}

	@Transactional
	@Auditable(action = "update a client")
	@Override
	public Account updateAccount(Long id, Account a) {
		a.setId(id);
		return this.accountRepository.save(a);
	}

	@Override
	@Transactional
	@Auditable(action = "reset password")
	public void reset(Account account) throws MessagingException {
		Account a = this.accountRepository.findByUsername(account.getUsername());
		if ((a != null) && a.getEmail().equals(account.getEmail())) {
			String token = generateToken();
			ResetPassword rp = ResetPassword.builder().account(a).token(token)
					.validity(new Date(System.currentTimeMillis() + 3600000))// 1 heure
					.build();
			if (a.getResetPassword() != null) {
				rp.setId(a.getResetPassword().getId());
			}
			this.resetPasswordRepository.save(rp);
			MailDTO mail = MailDTO.builder().from(this.from).to(a.getEmail()).subject("Reset password verification")
					.template("resetPassword").img("welcom.png").build();
			Map<String, Object> props = new HashMap<>();
			props.put("token", token);
			props.put("sign", this.sign);
			props.put("location", this.location);
			mail.setProps(props);
			this.mailService.sendMail(mail);
		} else {
			throw new AccessDeniedException("username or email incorrect");
		}
	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}

}
