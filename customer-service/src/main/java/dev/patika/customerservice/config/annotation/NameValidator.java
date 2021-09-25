package dev.patika.customerservice.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name,String> {

    private static final String NAME_REGEX = "^[\\p{L}]+([(\\s)$]?+[\\p{L}]+)+";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);


    @Override
    public void initialize(Name constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        return matcher.matches();
    }
}
