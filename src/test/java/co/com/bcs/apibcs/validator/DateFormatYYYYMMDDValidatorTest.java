package co.com.bcs.apibcs.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.com.bcs.apibcs.validators.DateFormatYYYYMMDDValidator;

 class DateFormatYYYYMMDDValidatorTest {
   
    @Test
     void testFechaFormatOK(){

        DateFormatYYYYMMDDValidator validator = new DateFormatYYYYMMDDValidator();
        boolean result = validator.isValid("2020-01-01", null);
        assertTrue(result,"Result no OK");
    }

    @Test
     void testFechaFormatER(){
        DateFormatYYYYMMDDValidator validator = new DateFormatYYYYMMDDValidator();
        boolean result = validator.isValid("20201201", null);
        assertFalse(result,"Result no OK");
    }

    @Test
     void testFechaNull(){
        DateFormatYYYYMMDDValidator validator = new DateFormatYYYYMMDDValidator();
        boolean result = validator.isValid(null, null);
        assertFalse(result,"Result no OK");
    }
    }