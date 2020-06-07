package my.pro.job.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JWTProvider {
	
	
	public static HttpServletResponse addAuthorizedHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE,PATCH,HEAD");
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With,"
				+ "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Contol-Allow-Origin,"
				+ " Access-Control-Allow-Credentials, Authorization");
		return response;
	}
	
	public static boolean ifOption(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpStatus.SC_OK);
			return true;
		}
		return false;
	}
	
	public static String resolveJWT(HttpServletRequest request){
		String jwt = request.getHeader("Authorization");
		if( jwt != null && jwt.startsWith("Bearer ")) {
			jwt = jwt.substring(7);
			return jwt;
		}
		return "";
	}
	
	public static boolean validateJwt(String jwt, String secret) 
				throws SignatureException, ServletException{
		Jws<Claims> parsedJwt =parseJwt(jwt, secret);
		if(!isExpired(parsedJwt.getBody().getExpiration())) return true;
		else {
			throw new SignatureException("the token has expired");
		}
	}
	
	public static Jws<Claims> parseJwt(String jwt, String secret){
		return Jwts
				.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
					.parseClaimsJws(jwt);
	}
	
	private static boolean isExpired(Date date) {
		return 
			date.before(new Date(System.currentTimeMillis()))? true : false;
	}
	
	public static String generateJWT(String username,List<String> roles, String secret) {
		
		String jwt = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 300000))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()))
				.claim("roles",roles)
				.compact();
		
		return jwt;
	}
}
