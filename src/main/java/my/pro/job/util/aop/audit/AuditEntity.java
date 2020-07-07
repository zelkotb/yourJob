package my.pro.job.util.aop.audit;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public interface AuditEntity {

	/**
	 * for supplementary information about the Entity
	 * @return
	 * entity fields separated by ","
	 * example name,lastName
	 */
	public abstract String auditDescription();
}
