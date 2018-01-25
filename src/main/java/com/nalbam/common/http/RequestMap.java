package com.nalbam.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nalbam.common.exception.InternalServerErrorException;
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
import java.util.Map;

@Slf4j
public class RequestMap {

    public static Map get(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final MultiValueMap<String, String> param) {
        return request(restTemplate, HttpMethod.GET, url, headers, param, null);
    }

    public static Map post(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.POST, url, headers, null, body);
    }

    public static Map put(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.PUT, url, headers, null, body);
    }

    public static Map delete(final RestTemplate restTemplate, final String url, final HttpHeaders headers) {
        return request(restTemplate, HttpMethod.DELETE, url, headers, null, null);
    }

    private static Map request(RestTemplate restTemplate, final HttpMethod method, final String url, HttpHeaders headers, final MultiValueMap<String, String> param, final Object object) {
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

            log.debug("RequestMap exchange {} : {} : {}", method, uri, response.getStatusCode());
            log.debug("RequestMap exchange {}", response.getBody());

            if (StringUtils.isEmpty(response.getBody())) {
                return null;
            }

            body = response.getBody();

            return new ObjectMapper().readValue(body, Map.class);
        } catch (final HttpClientErrorException e) {
            log.warn("RequestMap exchange Error 1 : {} : {} : {} : {}", e.getMessage(), method, uri, body);
            log.warn("RequestMap exchange Error 1 : {}", e.getResponseBodyAsString());

            if (StringUtils.isEmpty(e.getResponseBodyAsString())) {
                return null;
            }

            body = e.getResponseBodyAsString();

            try {
                return new ObjectMapper().readValue(body, Map.class);
            } catch (final IOException e1) {
                log.error("RequestMap exchange Error 3 : {}", e1.getMessage());
            }
        } catch (final Exception e) {
            log.error("RequestMap exchange Error 2 : {} : {} : {}", e.getMessage(), method, uri);
        }

        throw new InternalServerErrorException("");
    }

}