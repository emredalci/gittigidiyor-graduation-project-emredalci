package dev.patika.customerservice.config.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "Invalid first name or last name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
