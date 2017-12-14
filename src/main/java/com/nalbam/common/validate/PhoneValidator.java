package com.nalbam.common.validate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    private static final String MOBILE_PATTERN = "^\\d{9,11}|0(?:2|\\d{2})-\\d{3,4}-\\d{4}$";

    @Override
    public void initialize(final ValidPhone validPhone) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || validate(value);
    }

    public boolean validate(final String value) {
        final Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
