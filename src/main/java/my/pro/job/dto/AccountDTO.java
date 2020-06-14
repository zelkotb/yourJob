package my.pro.job.dto;

import java.util.List;

import my.pro.job.entity.Role;

/**
 * 
 * @author Elkotb Zakaria
 *
 */
public class AccountDTO {

	private String username;
	private String email;
	private String password;
	private List<Role> roles;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
