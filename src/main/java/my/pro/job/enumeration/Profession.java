package my.pro.job.enumeration;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public enum Profession {
	ENGINEER("ENGINEER"),
	CONSULTANT("CONSULTANT"),
	DEVELOPER("DEVELOPER"),
	DATASCIENTIST("DATA SCIENTIST"),
	TESTER("TESTER"),
	SYSTEMADMINISTRATOR("SYSTEM ADMINISTRATOR");
	
	private String profession;
	 
	private Profession(String profession) {
		this.profession=profession;
	}
	public String getProfession() {
		return this.profession;
	}
}
