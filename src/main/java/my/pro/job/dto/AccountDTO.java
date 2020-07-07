package my.pro.job.dto;

import java.util.List;

/**
 *
 * @author Elkotb Zakaria
 *
 */
public class AccountDTO {

	private Long id;
	private String username;
	private String email;
	private String password;
	private List<String> roles;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return this.roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
