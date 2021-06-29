package com.codeofeverything.backendmarketstaresearch.controller;

import com.codeofeverything.backendmarketstaresearch.model.response.CompanyOverviewResponse;
import com.codeofeverything.backendmarketstaresearch.service.overview.CompanyOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/overview")
public class CompanyOverviewController {

  final CompanyOverviewService companyOverviewService;

  @Autowired
  public CompanyOverviewController(final CompanyOverviewService companyOverviewService) {
    this.companyOverviewService = companyOverviewService;
  }

  @GetMapping("/find/symbol/{symbol}")
  public CompanyOverviewResponse findSymbolOverview(@PathVariable("symbol") String symbol){
    final String uppercaseSymbol = symbol.toUpperCase();

    return this.companyOverviewService.findCompanyOverviewBySymbolFromAPI(uppercaseSymbol);
  }
}
