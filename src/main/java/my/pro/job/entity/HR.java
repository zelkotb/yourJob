package my.pro.job.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
@Table(name = "hr")
public class HR extends Person{

	@OneToMany(mappedBy = "recruter")
	private List<Offer> offers;
	@ManyToOne()
	@JoinColumn(name = "city")
	private City city;
	@JsonIgnoreProperties
	@OneToOne(mappedBy = "recruter")
	private Account account;
	@ManyToMany()
	@JoinTable(
			name = "recruter_language",
			joinColumns = @JoinColumn(name = "recruter_id"),
			inverseJoinColumns = @JoinColumn(name = "language_id"))
	private List<Language> languages;
	
	public HR() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
}
