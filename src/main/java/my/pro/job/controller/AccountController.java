package my.pro.job.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('RECRUTER')")
	@Operation(security = {@SecurityRequirement(name = "Bearer Token")})
	public Account saveAccount(@RequestBody AccountDTO a) {
		return accountService.saveAccount(
				this.modelMapper
					.map(a, Account.class));
	}
}
