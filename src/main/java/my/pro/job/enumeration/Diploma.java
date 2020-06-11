package my.pro.job.enumeration;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public enum Diploma {
	DOCTORATE("DOCTORATE"),
	SPECIALIZEDMASTER("SPECIALIZED MASTER"),
	ENGINEER("ENGINEER"),
	MASTER("MASTER"),
	LICENCE("LICENCE"),
	SPECIALIZEDTECHNICIAN("SPECIALIZED TECHNICIAN"),
	TECHNICIAN("TECHNICIAN"),
	DUT("DUT"),
	BTS("BTS"),
	BACHELOR("BACHELOR"),
	OTHER("OTHER");
	
	private String degree;

	private Diploma(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}
	
}
