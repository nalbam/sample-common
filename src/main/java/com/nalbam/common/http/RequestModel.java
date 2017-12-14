package com.nalbam.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nalbam.common.exception.InternalServerErrorException;
import com.nalbam.common.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;

@Slf4j
public class RequestModel {

    public static ResultModel get(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final MultiValueMap<String, String> param) {
        return request(restTemplate, HttpMethod.GET, url, headers, param, null);
    }

    public static ResultModel post(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.POST, url, headers, null, body);
    }

    public static ResultModel put(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.PUT, url, headers, null, body);
    }

    public static ResultModel delete(final RestTemplate restTemplate, final String url, final HttpHeaders headers) {
        return request(restTemplate, HttpMethod.DELETE, url, headers, null, null);
    }

    private static ResultModel request(RestTemplate restTemplate, final HttpMethod method, final String url, HttpHeaders headers, final MultiValueMap<String, String> param, final Object object) {
        if (restTemplate == null) {
            restTemplate = Request.getRestTemplate();
        }

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setAccept(Collections.singletonList(new MediaType("application", "json", Charset.forName("UTF-8"))));
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        final HttpEntity<String> entity = Request.buildEntity(object, headers);

        final URI uri = Request.buildUri(url, param);

        String body = null;

        try {
            final ResponseEntity<String> response = restTemplate.exchange(uri, method, entity, String.class);

            log.debug("RequestModel exchange {} : {} : {}", method, uri, response.getStatusCode());
            log.debug("RequestModel exchange {}", response.getBody());

            if (StringUtils.isEmpty(response.getBody())) {
                return null;
            }

            body = response.getBody();

            return new ObjectMapper().readValue(body, ResultModel.class);
        } catch (final HttpClientErrorException e) {
            log.warn("RequestModel exchange Error 1 : {} : {} : {} : {}", e.getMessage(), method, uri, body);
            log.warn("RequestModel exchange Error 1 : {}", e.getResponseBodyAsString());

            if (StringUtils.isEmpty(e.getResponseBodyAsString())) {
                return null;
            }

            body = e.getResponseBodyAsString();

            try {
                return new ObjectMapper().readValue(body, ResultModel.class);
            } catch (final IOException e1) {
                log.error("RequestModel exchange Error 3 : {}", e1.getMessage());
            }
        } catch (final Exception e) {
            log.error("RequestModel exchange Error 2 : {} : {} : {}", e.getMessage(), method, uri);
        }

        throw new InternalServerErrorException("");
    }

}
