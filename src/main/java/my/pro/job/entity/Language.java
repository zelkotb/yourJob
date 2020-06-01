package my.pro.job.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
public class Language {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "languages")
	private List<Candidate> candidates;
	@ManyToMany(mappedBy = "languages")
	private List<HR> recruters;
	
	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Candidate> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	public List<HR> getRecruters() {
		return recruters;
	}
	public void setRecruters(List<HR> recruters) {
		this.recruters = recruters;
	}
	
	
}
