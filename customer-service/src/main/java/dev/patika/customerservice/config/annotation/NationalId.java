package dev.patika.customerservice.config.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalIdValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalId {
    String message() default "Invalid national id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
