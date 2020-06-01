package my.pro.job.util.exception;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
		return new ResponseEntity<>(customException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException exception){
		LOG.error("unxpected error has happend ",exception);
		return new ResponseEntity<>("Access denied",HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception exception){
		LOG.error("unxpected error has happend ",exception);
		return new ResponseEntity<>("Internal error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
