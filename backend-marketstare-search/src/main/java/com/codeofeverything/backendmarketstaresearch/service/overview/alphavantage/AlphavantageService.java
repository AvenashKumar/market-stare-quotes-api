package com.codeofeverything.backendmarketstaresearch.service.overview.alphavantage;

import com.codeofeverything.backendmarketstaresearch.model.AlphavantageFunctionType;
import com.codeofeverything.backendmarketstaresearch.model.response.CompanyOverviewResponse;
import com.codeofeverything.backendmarketstaresearch.model.response.Ticker;
import com.codeofeverything.backendmarketstaresearch.service.overview.IOverview;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Slf4j
public class AlphavantageService implements IOverview {

  private final String apiKey;
  private final AlphavantageClient alphavantageClient;

  public AlphavantageService(
      @Value("${market.data.alphavantage.apikey}") final String apiKey,
      @Autowired final AlphavantageClient alphavantageClient) {
    this.apiKey = apiKey;
    this.alphavantageClient = alphavantageClient;
  }

  private MultiValueMap<String, String> buildRequestMap(
      final AlphavantageFunctionType functionType) {
    MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
    requestMap.add("function", functionType.name());
    requestMap.add("apikey", apiKey);
    return requestMap;
  }

  private MultiValueMap<String, String> buildRequestMap(
      final String symbol, final AlphavantageFunctionType functionType) {
    MultiValueMap<String, String> requestMap = buildRequestMap(functionType);
    requestMap.add("symbol", symbol);
    return requestMap;
  }

  @Override
  public Optional<CompanyOverviewResponse> findCompanyOverview(final String symbol) {
    MultiValueMap<String, String> requestMap =
        buildRequestMap(symbol, AlphavantageFunctionType.OVERVIEW);

    final CompanyOverviewResponse companyOverviewResponse =
        this.alphavantageClient.execute(requestMap, CompanyOverviewResponse.class);

    if (companyOverviewResponse.getSymbol() == null) {
      return Optional.empty();
    }

    return Optional.of(companyOverviewResponse);
  }

  public Set<Ticker> findAllTickers() {

    MultiValueMap<String, String> requestMap =
        buildRequestMap(AlphavantageFunctionType.LISTING_STATUS);

    byte[] response = this.alphavantageClient.execute(requestMap, byte[].class);

    return parseListingStatus(new String(response, StandardCharsets.UTF_8));
  }

  private Set<Ticker> parseListingStatus(final String listingStatus) {
    Set<Ticker> allTickers = new HashSet<>();
    if (StringUtils.isBlank(listingStatus)) return allTickers;

    String[] tickersListingWithHeader = listingStatus.split("\r\n");
    String[] tickersListing =
        ArrayUtils.subarray(tickersListingWithHeader, 1, tickersListingWithHeader.length);

    for (String tickerListing : tickersListing) {
      String[] arrTickerListing = tickerListing.split(",");

      if (arrTickerListing.length < 2) {
        log.info("Invalid response is received from listing API.");
        return allTickers;
      }

      allTickers.add(
          Ticker.builder().ticker(arrTickerListing[0]).name(arrTickerListing[1]).build());
    }

    return allTickers;
  }
}
