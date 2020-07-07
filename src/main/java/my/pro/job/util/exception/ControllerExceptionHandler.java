package my.pro.job.util.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import io.jsonwebtoken.JwtException;
import my.pro.job.dto.ExceptionDTO;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> customExceptionHandler(CustomException customException){
		LOG.error("unxpected error has happend ",customException.getCause());
		ExceptionDTO exceptionDTO = initExceptionDTO(customException, customException.getMessage(), 400, "Bad Request");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception, exception.getMessage(), 401, "Forbidden");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<?> authenticationException(AuthenticationException exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception, exception.getMessage(), 403, "Forbidden");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(JwtException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<?> jwtException(JwtException exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception, exception.getMessage(), 403, "Forbidden");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> exception(Exception exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception,"Internal Error", 500, "Internal Server error");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ExceptionDTO initExceptionDTO(Exception e, String message, int status, String error) {
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setTimestamp(LocalDateTime.now().toString());
		exceptionDTO.setStatus(status);
		exceptionDTO.setError(error);
		String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		exceptionDTO.setPath(path);
		exceptionDTO.setMessage(message);
		
		
		return exceptionDTO;
	}
}
