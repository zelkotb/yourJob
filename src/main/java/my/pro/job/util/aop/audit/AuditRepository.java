package my.pro.job.util.aop.audit;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public interface AuditRepository extends MongoRepository<AuditDTO, String>{

}
