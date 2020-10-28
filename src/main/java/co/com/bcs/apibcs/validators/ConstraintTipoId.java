package co.com.bcs.apibcs.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ConstraintTipoIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstraintTipoId {
    
    String message() default "Tipo Id No valido";

    boolean nullable() default false;

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}