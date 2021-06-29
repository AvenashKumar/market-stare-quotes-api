package com.codeofeverything.backendmarketstaresearch.service.overview.alphavantage;

import com.codeofeverything.backendmarketstaresearch.service.overview.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphavantageClient implements IClient {
  private final String apiEndpoint;

  private final RestTemplate restTemplate;

  public AlphavantageClient(@Value("${market.data.alphavantage.api-endpoint}") String apiEndpoint,
      @Autowired RestTemplate restTemplate) {
    this.apiEndpoint = apiEndpoint;
    this.restTemplate = restTemplate;
  }


  @Override
  public <T> T execute(MultiValueMap<String, String> queryParams,
      Class<T> responseType, Object... uriVariables) {
    return execute(restTemplate, apiEndpoint, queryParams, responseType);
  }
}
