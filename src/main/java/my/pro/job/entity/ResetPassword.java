package my.pro.job.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "reset_password")
@Builder
@AllArgsConstructor
public class ResetPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@Temporal(TemporalType.TIMESTAMP)
	private Date validity;
	private boolean used;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Account account;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getValidity() {
		return this.validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isUsed() {
		return this.used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
