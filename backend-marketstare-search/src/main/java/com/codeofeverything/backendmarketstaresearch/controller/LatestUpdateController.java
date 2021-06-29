package com.codeofeverything.backendmarketstaresearch.controller;

import com.codeofeverything.backendmarketstaresearch.model.response.LatestUpdateResponse;
import com.codeofeverything.backendmarketstaresearch.service.LatestUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/latestupdate")
public class LatestUpdateController {
  private final LatestUpdateService latestUpdateService;

  public LatestUpdateController(
     @Autowired LatestUpdateService latestUpdateService) {
    this.latestUpdateService = latestUpdateService;
  }

  @GetMapping("/find/symbol/{symbol}")
  public LatestUpdateResponse findSymbolLatestUpdate(@PathVariable("symbol") String symbol){
    final String uppercaseSymbol = symbol.toUpperCase();

    return latestUpdateService.findLatestUpdate(uppercaseSymbol);
  }

}
