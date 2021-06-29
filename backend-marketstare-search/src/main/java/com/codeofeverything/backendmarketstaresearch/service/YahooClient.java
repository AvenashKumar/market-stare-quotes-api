package com.codeofeverything.backendmarketstaresearch.service;

import com.codeofeverything.backendmarketstaresearch.exception.SymbolNotFoundException;
import com.codeofeverything.backendmarketstaresearch.model.response.LatestUpdateResponse;
import com.codeofeverything.backendmarketstaresearch.service.overview.IClient;
import com.codeofeverything.backendmarketstaresearch.service.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class YahooClient implements IClient {

  private final String apiEndpoint;

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  private final JsonUtils jsonUtils;

  @Autowired
  public YahooClient(@Value("${market.data.yahoo.api-endpoint}") String apiEndpoint,
      RestTemplate restTemplate,
      ObjectMapper objectMapper,
      JsonUtils jsonUtils) {
    this.apiEndpoint = apiEndpoint;
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.jsonUtils = jsonUtils;
  }

  @Override
  public <T> T execute(MultiValueMap<String, String> queryParams,
      Class<T> responseType, Object... uriVariables) {
    return execute(restTemplate, apiEndpoint, queryParams, responseType);
  }

  public LatestUpdateResponse execute(final String symbol, MultiValueMap<String, String> queryParams) {
    final String apiEndpoint = String.format("%s/%s",this.apiEndpoint, symbol);
    final String jsonResponse = execute(restTemplate, apiEndpoint, queryParams, String.class);

    LatestUpdateResponse latestUpdateResponse = new LatestUpdateResponse();
    try {
      final JsonNode jsonNode = objectMapper.readTree(jsonResponse);
      final JsonNode chartNode = jsonNode.get("chart");
      if(chartNode==null)
        throw new SymbolNotFoundException(String.format("Symbol '%s' not found.", symbol));

      JsonNode resultNode = chartNode.get("result");
      if(resultNode==null)
        throw new SymbolNotFoundException(String.format("Symbol '%s' not found.", symbol));

      resultNode = resultNode.get(0);

      final JsonNode metaNode = resultNode.get("meta");
      final JsonNode quoteNode = resultNode.get("indicators").get("quote").get(0);
      latestUpdateResponse = LatestUpdateResponse.builder()
          .marketPrice(jsonUtils.fetchValueForProvidedKey(metaNode, "regularMarketPrice"))
          .previousClose(jsonUtils.fetchValueForProvidedKey(metaNode,"chartPreviousClose"))
          .open(jsonUtils.fetchValueForProvidedKey(quoteNode, "open", true))
          .high(jsonUtils.fetchValueForProvidedKey(quoteNode, "high", true))
          .low(jsonUtils.fetchValueForProvidedKey(quoteNode, "low", true))
          .build();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return latestUpdateResponse;
  }
}
