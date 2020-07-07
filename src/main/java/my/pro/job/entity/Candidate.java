package my.pro.job.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import my.pro.job.enumeration.Profil;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
@Table(name = "candidate")
public class Candidate extends Person{

	@OneToMany(mappedBy = "candidate")
	private List<Application> applications;
	@Enumerated(EnumType.STRING)
	private Profil profil;
	@ManyToMany
	@JoinTable(
			name = "candidate_skill",
			joinColumns = @JoinColumn(name = "candidate_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
	private List<Formation> formations;
	@ManyToOne()
	@JoinColumn(name = "city")
	private City city;
	@OneToOne()
	private Account account;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
	private List<Experience> experiences;
	@ManyToMany()
	@JoinTable(
			name = "candidate_language",
			joinColumns = @JoinColumn(name = "candidate_id"),
			inverseJoinColumns = @JoinColumn(name = "language_id"))
	private List<Language> languages;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
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

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
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

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
}
