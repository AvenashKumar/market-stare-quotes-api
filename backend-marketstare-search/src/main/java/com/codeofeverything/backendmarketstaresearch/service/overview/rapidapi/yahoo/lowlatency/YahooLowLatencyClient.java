package com.codeofeverything.backendmarketstaresearch.service.overview.rapidapi.yahoo.lowlatency;

import com.codeofeverything.backendmarketstaresearch.service.overview.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class YahooLowLatencyClient implements IClient {

  private final YahooLowLatencyOptionsConfigs yahooLowLatencyOptionsConfigs;

  private final RestTemplate restTemplate;

  public YahooLowLatencyClient(
      @Autowired YahooLowLatencyOptionsConfigs yahooLowLatencyOptionsConfigs,
      @Autowired RestTemplate restTemplate) {
    this.yahooLowLatencyOptionsConfigs = yahooLowLatencyOptionsConfigs;
    this.restTemplate = restTemplate;
  }



  @Override
  public <T> T execute(
      MultiValueMap<String, String> queryParams, Class<T> responseType, Object... uriVariables) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("x-api-key", yahooLowLatencyOptionsConfigs.getApiKey());
    httpHeaders.set("x-api-host", yahooLowLatencyOptionsConfigs.getHost());
    return execute(
        restTemplate,
        yahooLowLatencyOptionsConfigs.getEndpoint(),
        httpHeaders,
        queryParams,
        responseType,
        uriVariables);
  }

  public <T> T execute(Class<T> responseType, Object... uriVariables) {
    return execute(null, responseType, uriVariables);
  }
}
