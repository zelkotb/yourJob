package my.pro.job.util.aop.audit;

import lombok.Builder;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Builder
public class AuditDTO {

	private String username;
	private String ipAddress;
	private String modificationDateTime;
	private String action;
	private String description;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getModificationDateTime() {
		return modificationDateTime;
	}
	public void setModificationDateTime(String modificationDateTime) {
		this.modificationDateTime = modificationDateTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public AuditDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuditDTO(String username, String ipAddress, String modificationDateTime, String action, String description) {
		super();
		this.username = username;
		this.ipAddress = ipAddress;
		this.modificationDateTime = modificationDateTime;
		this.action = action;
		this.description = description;
	}
	
	
	
}
