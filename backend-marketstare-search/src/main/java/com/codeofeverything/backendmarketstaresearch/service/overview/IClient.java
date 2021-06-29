package com.codeofeverything.backendmarketstaresearch.service.overview;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public interface IClient {
  <T> T execute(final MultiValueMap<String, String> queryParams,
      final Class<T> responseType, Object... uriVariables);

  default  <T> T execute(final RestTemplate restTemplate,
      final String apiEndpoint,
      final MultiValueMap<String, String> queryParams,
      final Class<T> responseType){

    return execute(restTemplate, apiEndpoint, null, queryParams, responseType);
  }

  default  <T> T execute(final RestTemplate restTemplate,
      final String apiEndpoint,
      final HttpHeaders headers,
      final MultiValueMap<String, String> queryParams,
      final Class<T> responseType,
      final Object... uriVariables){

    String uri;

    if (queryParams != null) {
      UriComponentsBuilder uriComponentsBuilder =
          UriComponentsBuilder.fromHttpUrl(apiEndpoint).queryParams(queryParams);
      uri = uriComponentsBuilder.toUriString();
    }else {
      uri = apiEndpoint;
    }

    HttpEntity<T> response = restTemplate.exchange(
        uri,
        HttpMethod.GET,
        new HttpEntity<>(headers),
        responseType,
        uriVariables);

    return response.getBody();
  }
}
