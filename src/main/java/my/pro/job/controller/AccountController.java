package my.pro.job.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import my.pro.job.dto.AccountDTO;
import my.pro.job.entity.Account;
import my.pro.job.service.AccountService;

/**
 * 
 * @author Elkotb Zakaria
 *
 */

@RestController
@RequestMapping("/job")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/account")
	@ResponseStatus(HttpStatus.CREATED)
	public Account saveAccount(@RequestBody AccountDTO a) {
		return accountService.saveAccount(
				this.modelMapper
					.map(a, Account.class));
	}
}
