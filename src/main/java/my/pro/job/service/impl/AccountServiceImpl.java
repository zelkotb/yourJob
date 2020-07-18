package my.pro.job.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.pro.job.dto.MailDTO;
import my.pro.job.dto.ResetPasswordDTO;
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

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Account findByEmail(String email) {
		return this.accountRepository.findByEmail(email);
	}

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
	@Auditable(action = "get token for reset password")
	public void createResetToken(Account account) throws MessagingException {
		Account a = this.accountRepository.findByUsername(account.getUsername());
		if ((a != null) && a.getEmail().equals(account.getEmail())) {
			String token = generateToken();
			ResetPassword rp = ResetPassword.builder().account(a).token(token)
					.validity(new Date(System.currentTimeMillis() + 3600000))// 1 heure
					.used(false).build();
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

	@Override
	@Transactional
	@Auditable(action = "reset Password")
	public void resetPassword(ResetPasswordDTO dto) throws CustomException {
		if (validateResetPasswordProcess(dto.getPassword(), dto.getRepeatedPassword(), dto.getEmail(),
				dto.getToken())) {
			Account a = findByEmail(dto.getEmail());
			a.setPassword(dto.getPassword());
			ResetPassword rp = this.modelMapper.map(dto, ResetPassword.class);
			rp.setValidity(a.getResetPassword().getValidity());
			rp.setUsed(true);
			rp.setId(a.getResetPassword().getId());
			String encodedPassword = this.bcrypt.encode(a.getPassword());
			a.setPassword(encodedPassword);
			rp.setAccount(a);
			this.resetPasswordRepository.save(rp);
		}
	}

	private String generateToken() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	@Override
	public boolean validateResetPasswordProcess(String password, String repeatPassword, String email, String token)
			throws CustomException {
		return (validatepassword(password, repeatPassword) && validateToken(email, token));
	}

	private boolean validatepassword(String password, String repeatPassword) throws CustomException {
		if (password.equals(repeatPassword)) {
			return true;
		} else {
			throw new CustomException("password and repeat password do not match");
		}
	}

	private boolean validateToken(String email, String token) throws CustomException {
		Account a = findByEmail(email);
		ResetPassword rp = a.getResetPassword();
		if ((rp != null) && (rp.getToken().equals(token))) {
			double generationTime = rp.getValidity().getTime();
			double currentTime = new Date().getTime();
			System.err.println(generationTime - currentTime);
			if ((generationTime - currentTime) >= 1800000) {// 30 min token validity
				if (rp.isUsed() == false) {
					return true;
				} else {
					throw new CustomException("this token is already used");
				}
			} else {
				throw new CustomException("this token is expired");
			}
		} else {
			throw new CustomException("this token is not valid");
		}
	}

}
