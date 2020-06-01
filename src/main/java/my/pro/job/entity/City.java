package my.pro.job.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
public class City {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	@OneToMany(mappedBy = "city")
	private List<HR> recruters;
	@OneToMany(mappedBy = "city")
	private List<Candidate> candidates;
	@OneToMany(mappedBy = "city")
	private List<Offer> offers;
	@OneToMany(mappedBy = "city")
	private List<School> schools;
	@OneToMany(mappedBy = "city")
	private List<Company> companies;
	@ManyToOne()
	@JoinColumn(name = "country")
	private Country country;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<HR> getRecruters() {
		return recruters;
	}

	public void setRecruters(List<HR> recruters) {
		this.recruters = recruters;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
}
