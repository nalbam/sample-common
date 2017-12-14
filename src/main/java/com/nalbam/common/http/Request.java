package com.nalbam.common.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLException;
import java.net.URI;

@Slf4j
public class Request {

    static RestTemplate getRestTemplate() {
        final Netty4ClientHttpRequestFactory nettyFactory = new Netty4ClientHttpRequestFactory();
        try {
            nettyFactory.setSslContext(SslContextBuilder.forClient().build());
        } catch (final SSLException e) {
            e.printStackTrace();
        }
        return new RestTemplate(nettyFactory);
    }

    static URI buildUri(final String url, final MultiValueMap<String, String> param) {
        final UriComponentsBuilder builder;
        if (param != null) {
            builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(param);
        } else {
            builder = UriComponentsBuilder.fromHttpUrl(url);
        }

        return builder.build().encode().toUri();
    }

    static HttpEntity<String> buildEntity(final Object body, final HttpHeaders headers) {
        String json = null;

        if (body != null) {
            try {
                json = new ObjectMapper().writeValueAsString(body);
            } catch (final JsonProcessingException e) {
                log.warn("Request buildEntity Error : {} : {}", e.getMessage(), body);
            }
        }

        final HttpEntity<String> entity;

        if (StringUtils.isEmpty(json)) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(json, headers);
        }

        return entity;
    }

}
