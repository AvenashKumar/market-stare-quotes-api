package com.codeofeverything.backendmarketstaresearch.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {
  public String fetchValueForProvidedKey(
      final JsonNode quote, final String key, final boolean isArray) {
    JsonNode node = quote.get(key);
    if (node == null) {
      return null;
    } else {
      if (isArray) {
        if(node.isEmpty()){
          return null;
        }
        return node.get(0).asText();
      } else {
        return node.asText();
      }
    }
  }

  public String fetchValueForProvidedKey(
      final JsonNode quote, final String key) {
    JsonNode node = quote.get(key);
    return fetchValueForProvidedKey(quote, key, false);
  }
}
