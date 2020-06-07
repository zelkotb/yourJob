package my.pro.job.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class AuthorizationFilter extends OncePerRequestFilter{

	@Value("${security.jwt.secret}")
	private String secret;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(!JWTProvider.ifOption(request, response)) {
			String jwt = JWTProvider.resolveJWT(request);
			if(!jwt.equals("")) {
				if(JWTProvider.validateJwt(jwt,secret)) {
					authUser(jwt);
				}
			}
		}else {
			response = JWTProvider.addAuthorizedHeaders(response);
		}
		filterChain.doFilter(request, response);
	}
	
	private void authUser(String jwt) {
		Jws<Claims> parsedJwt = JWTProvider.parseJwt(jwt,secret);
		String username = parsedJwt.getBody().getSubject();
		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) parsedJwt.getBody().get("roles");
		List<GrantedAuthority> authorities = roles
					.stream()
						.map(r->new SimpleGrantedAuthority(r))
							.collect(Collectors.toList());
		SecurityContextHolder
			.getContext()
				.setAuthentication(
						new UsernamePasswordAuthenticationToken(username, null, authorities)
						);
	}

}
