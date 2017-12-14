package com.nalbam.common.validate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements ConstraintValidator<ValidNotNull, Object> {

    @Override
    public void initialize(final ValidNotNull validNotNull) {
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value instanceof Long || value instanceof Integer) {
            return !StringUtils.isEmpty(value) && Long.parseLong(value.toString()) > 0;
        } else {
            return !StringUtils.isEmpty(value);
        }
    }

}
