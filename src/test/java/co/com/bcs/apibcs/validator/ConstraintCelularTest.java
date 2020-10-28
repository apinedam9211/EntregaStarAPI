package co.com.bcs.apibcs.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.com.bcs.apibcs.validators.ConstraintCelular;

class ConstraintCelularTest {

    Validator validator;

    @BeforeEach
    void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    class CelularTest {

        @ConstraintCelular(nullable = true)
        String celularNullable;
    }

    @Test
    void celularNullable() {

        CelularTest celularTest = new CelularTest();
        Set<ConstraintViolation<CelularTest>> result = validator.validate(celularTest);
        assertEquals(0, result.size());

    }
}