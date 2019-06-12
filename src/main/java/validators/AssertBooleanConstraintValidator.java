package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssertBooleanConstraintValidator implements ConstraintValidator <AssertBoolean, String> {
    @Override
    public boolean isValid(String line, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("AssertBooleanConstraintValidator - start");
        return "true".equals(line.toLowerCase())|| "false".equals(line.toLowerCase());
    }
}
