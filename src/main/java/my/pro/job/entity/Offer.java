package my.pro.job.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import my.pro.job.enumeration.Profil;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
@Table(name = "offer")
public class Offer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Size(min = 50)
	private String description;
	@Enumerated(EnumType.STRING)
	private Profil profil;
	@ManyToMany()
	@JoinTable(
			name = "offer_skill",
			joinColumns = @JoinColumn(name = "offer_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;
	@OneToMany(mappedBy = "offer")
	private List<Application> applications;
	@ManyToOne()
	@JoinColumn(name = "recruter")
	private HR recruter;
	@ManyToOne()
	@JoinColumn(name = "company")
	private Company company;
	@ManyToOne()
	@JoinColumn(name = "city")
	private City city;
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public HR getRecruter() {
		return recruter;
	}

	public void setRecruter(HR recruter) {
		this.recruter = recruter;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
