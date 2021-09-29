package dev.patika.customerservice.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NationalIdValidator implements ConstraintValidator<NationalId,String> {

    private static final String NATIONAL_ID_REGEX = "^[1-9]{1}[0-9]{9}[02468]{1}$";
    private static final Pattern NATIONAL_ID_PATTERN = Pattern.compile(NATIONAL_ID_REGEX);

    @Override
    public void initialize(NationalId constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nationalId, ConstraintValidatorContext context) {
        Matcher matcher=NATIONAL_ID_PATTERN.matcher(nationalId!=null?nationalId:"");
        return matcher.matches();
    }
}
