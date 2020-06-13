package my.pro.job.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 
 * @author Elkotb Zakaria
 *
 */

public class PasswordConstraintValidator implements ConstraintValidator<Password, String>{

	private String password;
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		this.password=password;
		System.err.println(this.password);
		if(isMoreThan8() &&
			hasSpace() &&
			hasUpperLowerCase() &&
			hasNumber() &&
			hasSpecialChar()) {
			return true;
		}
		System.err.println("heloooo");
		return false;
	}
	
	private boolean isMoreThan8() {
		if(this.password.length()>8) {
			System.err.println(this.password.length());
			return true;
		};
		return false;
	}
	
	private boolean hasUpperLowerCase() {
		return (!this.password.equals(this.password.toUpperCase())
				&& !this.password.equals(this.password.toLowerCase()));
	}
	
	private boolean hasSpecialChar() {
		return !this.password.matches("[A-Za-z0-9]*");
	}
	
	private boolean hasNumber() {
		for (int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))) return true;
		}
		return false;
	}
	
	private boolean hasSpace() {
		return this.password.equals(this.password.trim());
	}

}
