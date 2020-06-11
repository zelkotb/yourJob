package my.pro.job.util.aop.audit;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Aspect
@Configuration
@Component
@EnableMongoRepositories("my.pro.job.util.aop.audit")
public class AuditAspect {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AuditService auditService;

	@After("@annotation(auditable)")
	@Transactional
	private void doAudit(JoinPoint joinpoint, Auditable auditable) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		String username = currentUsername.equals("anonymousUser") ? "Anonymous" : currentUsername;
		String ipAddress = request.getRemoteAddr();
		String modificationDateTime = LocalDateTime.now().toString();
		String action = auditable.action();
		String description = "";
		Object[] args = joinpoint.getArgs();
		if(args.length!=0) {
			for (Object object : args) {
				if (object instanceof AuditEntity) {
					description = ((AuditEntity) object).auditDescription();
				}
			}
		}
		AuditDTO auditDTO = AuditDTO.builder().username(username)
			.ipAddress(ipAddress)
				.modificationDateTime(modificationDateTime)
					.action(action)
						.description(description).build();
		auditService.save(auditDTO);
		
	}
}
