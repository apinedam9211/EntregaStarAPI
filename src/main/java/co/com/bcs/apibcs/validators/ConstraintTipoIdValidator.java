package co.com.bcs.apibcs.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.com.bcs.apibcs.services.dto.TipoIdentificacion;


public class ConstraintTipoIdValidator implements ConstraintValidator<ConstraintTipoId, String> {

    private boolean nullable;

    @Override
    public void initialize(ConstraintTipoId constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (nullable && value == null) {
            return true;
        }

        try {
            TipoIdentificacion.valueOf(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
