package com.codeofeverything.backendmarketstaresearch.service;

import com.codeofeverything.backendmarketstaresearch.entities.TickerEntity;
import com.codeofeverything.backendmarketstaresearch.model.response.Ticker;
import com.codeofeverything.backendmarketstaresearch.repo.TickerRepo;
import com.codeofeverything.backendmarketstaresearch.service.overview.alphavantage.AlphavantageService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllTickersService {

  private final AlphavantageService alphavantageService;

  private final TickerRepo tickerRepo;

  public AllTickersService(
      @Autowired AlphavantageService alphavantageService,
      @Autowired TickerRepo tickerRepo) {
    this.alphavantageService = alphavantageService;
    this.tickerRepo = tickerRepo;
  }

  private Set<Ticker> findAllTickersFromAPI() {
    Set<Ticker> allTickers = this.alphavantageService.findAllTickers();
    List<TickerEntity> tickerEntities = new ArrayList<>();
    allTickers.forEach(ticker -> tickerEntities.add(ticker2TickerEntity(ticker)));
    if (!tickerEntities.isEmpty()) {
      this.tickerRepo.deleteAll();
      this.tickerRepo.saveAll(tickerEntities);
    }
    return allTickers;
  }

  private Ticker tickerEntity2Ticker(final TickerEntity tickerEntity){
    return Ticker.builder()
        .name(tickerEntity.getName())
        .ticker(tickerEntity.getTicker())
        .build();
  }

  private TickerEntity ticker2TickerEntity(final Ticker ticker){
    return TickerEntity.builder()
        .name(ticker.getName())
        .ticker(ticker.getTicker())
        .build();
  }

  private Set<Ticker> findAllTickersFromDB() {
    List<TickerEntity> tickerEntities = this.tickerRepo.findAll();
    Set<Ticker> tickers = new HashSet<>();

    tickerEntities.forEach(tickerEntity -> tickers.add(tickerEntity2Ticker(tickerEntity)));

    return tickers;
  }

  public Set<Ticker> findAllTickers(){
    Set<Ticker> allTickers = findAllTickersFromDB();
    if (allTickers.isEmpty()) {
      allTickers = findAllTickersFromAPI();
    }
    return allTickers;
  }
}
