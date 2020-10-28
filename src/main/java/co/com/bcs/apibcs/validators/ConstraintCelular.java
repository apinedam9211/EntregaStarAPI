package co.com.bcs.apibcs.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ConstraintCelularValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstraintCelular {
    String message() default "Formato de campo celular incorrecto";

    boolean nullable() default false;

    int min() default 1;

    int max() default 12;

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
   
}