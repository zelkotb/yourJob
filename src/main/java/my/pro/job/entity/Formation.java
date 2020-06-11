package my.pro.job.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import my.pro.job.enumeration.Diploma;
import my.pro.job.enumeration.FormationType;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Entity
@Table(name = "formation")
public class Formation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date")
	private Date beginDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "formation_type")
	private FormationType formationType; //engineer master..?
	@Size(min = 50, max = 255)
	@Lob
	private String description;
	private Diploma diploma; //engineer bac+5 ...?
	@ManyToOne()
	@JoinColumn(name = "school")
	private School school;
	@ManyToOne()
	@JoinColumn(name="candidate")
	private Candidate candidate;
	
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public FormationType getFormationType() {
		return formationType;
	}

	public void setFormationType(FormationType formationType) {
		this.formationType = formationType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	
}
