package my.pro.job.util.exception;

import java.nio.file.AccessDeniedException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import my.pro.job.dto.ExceptionDTO;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> customExceptionHandler(CustomException customException){
		LOG.error("unxpected error has happend ",customException.getCause());
		ExceptionDTO exceptionDTO = initExceptionDTO(customException,customException.getMessage());
		return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception,exception.getMessage());
		return new ResponseEntity<>(exceptionDTO,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> authenticationException(AuthenticationException exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception,exception.getMessage());
		return new ResponseEntity<>(exceptionDTO,HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception exception){
		LOG.error("unxpected error has happend ",exception);
		ExceptionDTO exceptionDTO = initExceptionDTO(exception,"Internal Error");
		return new ResponseEntity<>(exceptionDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ExceptionDTO initExceptionDTO(Exception e, String message) {
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setMessage(message);
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd-MMM-yy HH:mm:ssZ");
		exceptionDTO.setDate(formatter.format(currentDate));
		return exceptionDTO;
	}
}
