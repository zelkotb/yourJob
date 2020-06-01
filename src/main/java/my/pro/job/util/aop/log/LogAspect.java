package my.pro.job.util.aop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Aspect
@Configuration
public class LogAspect {
	
	private Logger LOG;

	/**
	 * any modificator (public/private...)
	 * any method with any param in the controller package
	 * @param joinPoint
	 */
	@After("execution(* my.pro.job.controller.*.*(..))")
	private void doLog(JoinPoint joinPoint) {
		String className = joinPoint.getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		LOG = LoggerFactory.getLogger(className);
		LOG.info("the method "+methodName+" have been executed");
	}
}
