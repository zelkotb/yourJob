package my.pro.job.entity;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import my.pro.job.util.aop.audit.AuditEntity;

/*
	^                 # start-of-string
(?=.*[0-9])       # a digit must occur at least once
(?=.*[a-z])       # a lower case letter must occur at least once
(?=.*[A-Z])       # an upper case letter must occur at least once
(?=.*[@#$%^&+=])  # a special character must occur at least once
(?=\S+$)          # no whitespace allowed in the entire string
.{8,}             # anything, at least eight places though
$                 # end-of-string

*/
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
public class Account extends AuditEntity{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@Size(min = 5)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	@Size(min = 8)
	//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;
	@Column(nullable = false)
	//@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")
	private String email;
	
	/**
	 * if remove account remove person
	 */
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "recruter_id")
	private HR recruter;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToMany()
	@JoinTable(
		name = "account_role",
		joinColumns = @JoinColumn(name = "account_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private List<Role> roles;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public HR getRecruter() {
		return recruter;
	}

	public void setRecruter(HR recruter) {
		this.recruter = recruter;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String auditDescription() {
		return "email : "+email;
	}

}
