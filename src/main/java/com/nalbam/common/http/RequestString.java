package com.nalbam.common.http;

import com.nalbam.common.exception.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Collections;

@Slf4j
public class RequestString {

    public static String get(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final MultiValueMap<String, String> param) {
        return request(restTemplate, HttpMethod.GET, url, headers, param, null);
    }

    public static String post(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.POST, url, headers, null, body);
    }

    public static String put(final RestTemplate restTemplate, final String url, final HttpHeaders headers, final Object body) {
        return request(restTemplate, HttpMethod.PUT, url, headers, null, body);
    }

    public static String delete(final RestTemplate restTemplate, final String url, final HttpHeaders headers) {
        return request(restTemplate, HttpMethod.DELETE, url, headers, null, null);
    }

    private static String request(RestTemplate restTemplate, final HttpMethod method, final String url, HttpHeaders headers, final MultiValueMap<String, String> param, final Object object) {
        if (restTemplate == null) {
            restTemplate = Request.getRestTemplate();
        }

        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.setAccept(Collections.singletonList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
        headers.setContentType(new MediaType("text", "plain", Charset.forName("UTF-8")));

        final HttpEntity<String> entity = Request.buildEntity(object, headers);

        final URI uri = Request.buildUri(url, param);

        try {
            final ResponseEntity<String> response = restTemplate.exchange(uri, method, entity, String.class);

            log.debug("RequestString exchange {} : {} : {}", method, uri, response.getStatusCode());
            log.debug("RequestString exchange {}", response.getBody());

            return response.getBody();
        } catch (final HttpClientErrorException e) {
            log.warn("RequestString exchange Error 1 : {} : {} : {}", e.getMessage(), method, uri);
            log.warn("RequestString exchange Error 1 : {}", e.getResponseBodyAsString());

            return e.getResponseBodyAsString();
        } catch (final Exception e) {
            log.error("RequestString exchange Error 2 : {} : {} : {}", e.getMessage(), method, uri);
        }

        throw new InternalServerErrorException("");
    }

}
