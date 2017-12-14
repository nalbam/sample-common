package com.nalbam.common.validate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentityValidator implements ConstraintValidator<ValidIdentity, String> {

    private static final String ID_PATTERN = "^([_a-z0-9-]+)?$";

    @Override
    public void initialize(final ValidIdentity validIdentity) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return !StringUtils.isEmpty(value) && validate(value);
    }

    public boolean validate(final String value) {
        final Pattern pattern = Pattern.compile(ID_PATTERN);
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
