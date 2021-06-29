package com.codeofeverything.backendmarketstaresearch.service.overview.rapidapi.yahoo.lowlatency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "market.data.rapidapi.yahoo.lowlatency.options")
public class YahooLowLatencyOptionsConfigs {
  private String host;
  private String apiKey;
  private String endpoint;
}
