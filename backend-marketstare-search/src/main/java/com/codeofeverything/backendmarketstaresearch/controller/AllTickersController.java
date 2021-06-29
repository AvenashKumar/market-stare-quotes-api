package com.codeofeverything.backendmarketstaresearch.controller;

import com.codeofeverything.backendmarketstaresearch.model.response.Ticker;
import com.codeofeverything.backendmarketstaresearch.service.AllTickersService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/all")
public class AllTickersController {
  private final AllTickersService allTickersService;

  public AllTickersController(@Autowired AllTickersService allTickersService) {
    this.allTickersService = allTickersService;
  }

  @GetMapping("/tickers")
  public Set<Ticker> findAllStocks() {
    return allTickersService.findAllTickers();
  }
}
