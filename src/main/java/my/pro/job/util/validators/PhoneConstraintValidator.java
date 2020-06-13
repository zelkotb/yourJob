package my.pro.job.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String>{

	private String phone;
	
	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		this.phone = phone;
		if(
			beginWith() &&
			onlyDigits( )&&
			phone.length()<14 && phone.length()>9) return true;
		return false;
	}
	
	private boolean beginWith() {
		return (this.phone!=null &&
				(this.phone.charAt(0)=='0' || this.phone.charAt(0)=='+')
				);
	}
	private boolean onlyDigits() {
		try {
			Integer.parseInt(this.phone.substring(0));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
