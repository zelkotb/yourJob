package my.pro.job.controller;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import my.pro.job.dto.AccountDTO;
import my.pro.job.entity.Account;
import my.pro.job.service.AccountService;

/**
 * 
 * @author Elkotb Zakaria
 *
 */

@RestController
@RequestMapping("/my/pro/job/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Account register (@RequestBody AccountDTO a) throws MessagingException {
		return accountService.saveAccount(
				this.modelMapper
					.map(a, Account.class));
	}
	
	@PostMapping("/reset")
	@ResponseStatus(HttpStatus.CREATED)
	public void reset (@RequestBody AccountDTO a) throws MessagingException {
		accountService.reset(modelMapper.map(a, Account.class));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	@PreAuthorize("hasAuthority('RECRUTER')")
	@Operation(security = {@SecurityRequirement(name = "Bearer Token")})
	public void deleteAccount() {
		
	}
}
