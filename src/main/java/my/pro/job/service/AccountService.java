package my.pro.job.service;

import my.pro.job.entity.Account;

public interface AccountService {

	public Account saveAccount(Account a);
	public Account updateAccount(Long id, Account a);
}
