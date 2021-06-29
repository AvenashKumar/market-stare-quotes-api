package com.codeofeverything.backendmarketstaresearch.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatestUpdateResponse {
  @JsonProperty("MarketPrice")
  private String marketPrice;

  @JsonProperty("Open")
  private String open;

  @JsonProperty("PreviousClose")
  private String previousClose;

  @JsonProperty("Low")
  private String low;

  @JsonProperty("High")
  private String high;
}
