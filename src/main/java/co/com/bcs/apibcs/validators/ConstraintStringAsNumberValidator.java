package co.com.bcs.apibcs.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintStringAsNumberValidator implements ConstraintValidator<ConstraintStringAsNumber, String> {

    private boolean nullable;
    private int maxSize;

    @Override
    public void initialize(ConstraintStringAsNumber constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (nullable && value == null) {
            return true;
        }

        if(!nullable && value == null){
            return false;
        }

        if (maxSize< 1){
            return false;
        }

        String regex = (maxSize == 1 ? "^[1-9]$" :"^[1-9][0-9]{0," +  (maxSize-1) + "}$");
        return Pattern.matches(regex, value);
    }

}
