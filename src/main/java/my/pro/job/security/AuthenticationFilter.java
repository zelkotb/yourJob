package my.pro.job.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import my.pro.job.dto.AuthenticationDTO;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AuthenticationDTO account = null;
		try (InputStream in = request.getInputStream()){
			account = new ObjectMapper().readValue(in, AuthenticationDTO.class);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		String jwt = Jwts.builder().setSubject(user.getUsername())
						.setExpiration(new Date(System.currentTimeMillis() + 300000))
						.signWith(SignatureAlgorithm.HS512, "secret")
						.claim("roles",user.getAuthorities())
						.compact();
		response.addHeader("Authorization", "Bearer "+jwt);
	}
}
