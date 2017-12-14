package com.nalbam.common.validate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericValidator implements ConstraintValidator<ValidNumeric, Object> {

    private static final String NUMERIC_PATTERN = "^([0-9]+)?$";

    @Override
    public void initialize(final ValidNumeric validNumeric) {
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        if (value instanceof Long || value instanceof Integer) {
            return validate(value);
        } else if (value instanceof ArrayList) {
            for (final Object v : (List) value) {
                if (StringUtils.isEmpty(v) || !validate(v)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean validate(final Object value) {
        final Pattern pattern = Pattern.compile(NUMERIC_PATTERN);
        final Matcher matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

}
