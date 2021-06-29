package com.codeofeverything.backendmarketstaresearch.service.overview.rapidapi.yahoo.lowlatency;

import com.codeofeverything.backendmarketstaresearch.model.response.CompanyOverviewResponse;
import com.codeofeverything.backendmarketstaresearch.service.overview.IOverview;
import com.codeofeverything.backendmarketstaresearch.service.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YahooLowLatencyService implements IOverview {

  private final ObjectMapper objectMapper;
  private final YahooLowLatencyClient yahooLowLatencyClient;
  private final JsonUtils jsonUtils;

  @Autowired
  public YahooLowLatencyService(
      YahooLowLatencyClient yahooLowLatencyClient,
      ObjectMapper objectMapper,
      JsonUtils jsonUtils) {
    this.yahooLowLatencyClient = yahooLowLatencyClient;
    this.objectMapper = objectMapper;
    this.jsonUtils = jsonUtils;
  }

  @Override
  public Optional<CompanyOverviewResponse> findCompanyOverview(String symbol) {
    final String jsonResponse =
        this.yahooLowLatencyClient.execute(String.class, symbol);

    try {
      final JsonNode jsonNode = objectMapper.readTree(jsonResponse);
      final JsonNode results = jsonNode.get("optionChain").get("result").get(0);
      if(results == null){
        return Optional.empty();
      }
      final JsonNode quote = results.get("quote");

      return Optional.ofNullable(CompanyOverviewResponse.builder()
          .symbol(jsonUtils.fetchValueForProvidedKey(quote,"symbol"))
          .name(jsonUtils.fetchValueForProvidedKey(quote,"longName"))
          .fiftyTwoWeekHigh(jsonUtils.fetchValueForProvidedKey(quote,"fiftyTwoWeekHigh"))
          .fiftyTwoWeekLow(jsonUtils.fetchValueForProvidedKey(quote,"fiftyTwoWeekLow"))
          .marketCapitalization(jsonUtils.fetchValueForProvidedKey(quote,"marketCap"))
          .dividendPerShare(jsonUtils.fetchValueForProvidedKey(quote, "trailingAnnualDividendRate"))
          .dividendDate(jsonUtils.fetchValueForProvidedKey(quote,"dividendDate"))
          .sourceName(jsonUtils.fetchValueForProvidedKey(quote,"quoteSourceName"))
          .exchange(jsonUtils.fetchValueForProvidedKey(quote,"fullExchangeName"))
          .currency(jsonUtils.fetchValueForProvidedKey(quote,"currency"))
          .country(jsonUtils.fetchValueForProvidedKey(quote,"region"))
          .sharesOutstanding(jsonUtils.fetchValueForProvidedKey(quote,"sharesOutstanding"))
          .bookValue(jsonUtils.fetchValueForProvidedKey(quote,"bookValue"))
          .eps(jsonUtils.fetchValueForProvidedKey(quote,"epsTrailingTwelveMonths"))
          .type(jsonUtils.fetchValueForProvidedKey(quote, "quoteType"))
          .build());

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
