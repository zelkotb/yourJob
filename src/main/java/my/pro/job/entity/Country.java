package my.pro.job.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
@Table(name = "country")
public class Country {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String country;
	@OneToMany(mappedBy = "country")
	private List<City> cities;
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
	
}
