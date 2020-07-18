package my.pro.job.service;

import javax.mail.MessagingException;

import my.pro.job.dto.ResetPasswordDTO;
import my.pro.job.entity.Account;
import my.pro.job.util.exception.CustomException;

public interface AccountService {

	Account saveAccount(Account a) throws MessagingException, CustomException;

	Account updateAccount(Long id, Account a);

	void createResetToken(Account account) throws MessagingException;

	void resetPassword(ResetPasswordDTO dto) throws CustomException;

	Account findByEmail(String email);

	boolean validateResetPasswordProcess(String password, String repeatPassword, String email, String token)
			throws CustomException;
}
