package my.pro.job.util.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * 
 * @author Elkotb Zakaria
 *
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneConstraintValidator.class)
public @interface Phone {

	String message() default "Invalid Phone number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
