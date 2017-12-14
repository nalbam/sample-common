package com.nalbam.common.http;

import com.nalbam.common.result.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    public static ResponseEntity OK() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public static ResponseEntity OK(final Object body) {
        return new ResponseEntity<>(new ResultModel(HttpStatus.OK, body), HttpStatus.OK);
    }

    public static ResponseEntity CREATED() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public static ResponseEntity CREATED(final Object body) {
        return new ResponseEntity<>(new ResultModel(HttpStatus.CREATED, body), HttpStatus.CREATED);
    }

    public static ResponseEntity NO_CONTENT() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
