package com.codeofeverything.backendmarketstaresearch.service.overview;

import com.codeofeverything.backendmarketstaresearch.exception.SymbolNotFoundException;
import com.codeofeverything.backendmarketstaresearch.model.response.CompanyOverviewResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CompanyOverviewService {
  private final IOverview overviewService;

  @Autowired
  public CompanyOverviewService(
     @Qualifier("yahooLowLatencyService") final IOverview overviewService) {
    this.overviewService = overviewService;
  }

  public CompanyOverviewResponse findCompanyOverviewBySymbolFromAPI(final String symbol) {

    Optional<CompanyOverviewResponse> optCompanyOverview =
        overviewService.findCompanyOverview(symbol);

    if (optCompanyOverview.isEmpty()) {
      throw new SymbolNotFoundException(String.format("Symbol '%s' not found.", symbol));
    }

    return optCompanyOverview.get();
  }
}
