package co.com.bcs.apibcs.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ConstraintCelularValidator implements ConstraintValidator<ConstraintCelular, String> {

    private boolean nullable;
    private int min;
    private int max;

    @Override
    public void initialize(ConstraintCelular constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!this.nullable && value == null) {
            return false;
        }

        if (min > max) {
            return false;
        }

        if (value == null) {
            return true;
        }
        String regex = "";
        if (min == 0) {

            if ("".equals(value)) {
                return true;
            }
            regex = "^[1-9][0-9]{0," + (max - 1) + "}$";
        } else {
            regex = "^[1-9][0-9]{" + (min - 1) + "," + (max - 1) + "}$";
        }
        return Pattern.matches(regex, value);

    }
}