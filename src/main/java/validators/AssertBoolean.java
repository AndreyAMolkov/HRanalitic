package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(value= RetentionPolicy.RUNTIME)
@Constraint(validatedBy= AssertBooleanConstraintValidator.class)
public @interface AssertBoolean {
    String MESSAGE = "Введите только true или false";

    String message() default MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
