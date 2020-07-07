package my.pro.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import my.pro.job.dto.AuthenticationDTO;
import my.pro.job.dto.JwtDTO;
import my.pro.job.service.AuthenticationService;

@RestController
@RequestMapping("/my/pro/job")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/authenticate")
	@ResponseStatus(HttpStatus.OK)
	public JwtDTO authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
		String jwt = authenticationService.authenticateUser(authenticationDTO);
		JwtDTO response = new JwtDTO();
		response.setJwt(jwt);
		return response;
	}
}
