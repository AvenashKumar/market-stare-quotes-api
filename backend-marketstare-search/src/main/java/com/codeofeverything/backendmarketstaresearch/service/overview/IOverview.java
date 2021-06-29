package com.codeofeverything.backendmarketstaresearch.service.overview;

import com.codeofeverything.backendmarketstaresearch.model.response.CompanyOverviewResponse;
import java.util.Optional;

public interface IOverview {

  Optional<CompanyOverviewResponse> findCompanyOverview(String symbol);
}
