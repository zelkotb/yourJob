package my.pro.job.util.aop.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Service
public class AuditService {

	@Autowired
	private AuditRepository auditRepository;
	
	@Async
	public void save(AuditDTO audit) {
		auditRepository.save(audit);
	}
}
