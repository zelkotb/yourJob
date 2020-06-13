package my.pro.job.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import my.pro.job.enumeration.Nationality;
import my.pro.job.enumeration.Situation;
import my.pro.job.util.validators.Phone;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@MappedSuperclass
public class Person {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	@Phone
	private String phoneNumber;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String Address;
	@Column(name = "image_path")
	private String imagePath;
	@Column(name = "cv_path")
	private String cvPath;
	@Enumerated(EnumType.STRING)
	private Nationality nationality;
	@Enumerated(EnumType.STRING)
	private Situation situation;
	
	public Person() {
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCvPath() {
		return cvPath;
	}

	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}
	
}
