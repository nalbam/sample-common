package com.nalbam.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
public class ResultModel {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List errors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exception;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

    public ResultModel() {
    }

    public ResultModel(final Object data) {
        this(HttpStatus.OK, null, null, data, null);
    }

    public ResultModel(final HttpStatus status) {
        this(status, null, null, null, null);
    }

    public ResultModel(final HttpStatus status, final Integer code) {
        this(status, null, null, null, code);
    }

    public ResultModel(final HttpStatus status, final Object data) {
        this(status, null, null, data, null);
    }

    public ResultModel(final HttpStatus status, final String message) {
        this(status, message, null, null, null);
    }

    public ResultModel(final HttpStatus status, final String message, final Integer code) {
        this(status, message, null, null, code);
    }

    public ResultModel(final HttpStatus status, final String message, final List errors) {
        this(status, message, errors, null, null);
    }

    public ResultModel(final HttpStatus status, final String message, final Object data) {
        this(status, message, null, data, null);
    }

    public ResultModel(final HttpStatus status, final String message, final List errors, final Object data) {
        this(status, message, errors, data, null);
    }

    public ResultModel(final HttpStatus status, final String message, final List errors, final Object data, final Integer code) {
        this.timestamp = new Date();
        this.status = status.value();
        if (this.status >= HttpStatus.BAD_REQUEST.value()) {
            this.error = status.getReasonPhrase();
        }
        this.message = message;
        this.errors = errors;
        this.data = data;
        this.code = code;
    }

}
