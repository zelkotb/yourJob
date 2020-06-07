package my.pro.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import my.pro.job.dto.AuthenticationDTO;
import my.pro.job.entity.Account;
import my.pro.job.repository.AccountRepository;
import my.pro.job.security.JWTProvider;
import my.pro.job.service.AuthenticationService;

@Service
public class AuthenticationImplService implements AuthenticationService{

	@Value("${security.jwt.secret}")
	private String secret;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String authenticateUser(AuthenticationDTO authenticationDTO) 
			throws AuthenticationException{
		authenticationManger
			.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationDTO.getUsername(),
							authenticationDTO.getPassword()
							)
					);
		Account account = accountRepository.findByUsername(authenticationDTO.getUsername());
		List<String> roles = account
				.getRoles()
					.stream()
						.map(r->r.getRole()).collect(Collectors.toList());
		return JWTProvider.generateJWT(authenticationDTO.getUsername(),roles,secret);		
	}

}
