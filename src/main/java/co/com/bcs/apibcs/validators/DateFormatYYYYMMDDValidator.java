package co.com.bcs.apibcs.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatYYYYMMDDValidator implements ConstraintValidator<ConstraintDateFormatYYYYMMDD, String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (value == null) {
            return false;
        }

        try {
            format.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

}
