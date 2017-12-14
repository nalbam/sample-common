package com.nalbam.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtil {

    public static List<Map<String, Object>> getErrors(final BindingResult bindingResult) {
        final List<Map<String, Object>> errors = new ArrayList<>();

        if (bindingResult == null || bindingResult.getFieldErrors().size() == 0) {
            return errors;
        }

        Map<String, Object> map;

        for (final FieldError error : bindingResult.getFieldErrors()) {
            map = new HashMap<>();
            map.put("field", error.getField());
            map.put("message", error.getDefaultMessage());
            errors.add(map);
        }

        return errors;
    }

}
