package my.pro.job.enumeration;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public enum FormationType {
	TELECOM("TELECOM"),
	DATASCIENCE("DATA SCIENCE"),
	MANAGEMENT("MANAGEMENT"),
	COMPUTERSCIENCE("COMPUTER SCIENCE"),
	MEQUANIC("MEQUANIC"),
	ELECTRONIC("ELECTRONIC"),
	ELECTRIC("ELECTRIC"),
	EMBEDEDSYSTEM("EMBEDED SYSTEM");
	
	private String type;
	private FormationType(String type) {
		this.type=type;
	}
	public String getType() {
		return this.type;
	}
	
}
