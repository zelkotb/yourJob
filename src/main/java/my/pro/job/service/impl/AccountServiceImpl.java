package my.pro.job.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.pro.job.entity.Account;
import my.pro.job.repository.AccountRepository;
import my.pro.job.service.AccountService;
import my.pro.job.util.aop.audit.Auditable;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	@Transactional
	@Auditable(action = "create à client")
	public Account saveAccount(Account a) {
		String encodedPassword = bcrypt.encode(a.getPassword());
		a.setPassword(encodedPassword);
		return accountRepository.save(a);
	}

	
	@Transactional
	@Auditable(action = "update a client")
	@Override
	public Account updateAccount(Long id, Account a) {
		a.setId(id);
		return accountRepository.save(a);
	}
	
	
}
