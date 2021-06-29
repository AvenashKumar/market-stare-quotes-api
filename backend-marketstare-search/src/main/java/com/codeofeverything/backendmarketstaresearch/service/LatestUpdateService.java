package com.codeofeverything.backendmarketstaresearch.service;

import com.codeofeverything.backendmarketstaresearch.model.response.LatestUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class LatestUpdateService {
  private final YahooClient yahooClient;


  public LatestUpdateService(@Autowired YahooClient yahooClient) {
    this.yahooClient = yahooClient;
  }

  public LatestUpdateResponse findLatestUpdate(String uppercaseSymbol) {
    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.add("interval", "1d");

    return yahooClient.execute(uppercaseSymbol, multiValueMap);
  }
}
