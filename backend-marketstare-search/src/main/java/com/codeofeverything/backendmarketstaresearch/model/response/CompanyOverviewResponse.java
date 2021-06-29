package com.codeofeverything.backendmarketstaresearch.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CompanyOverviewResponse {
  @JsonProperty("Symbol")
  private String symbol;

  @JsonProperty("AssetType")
  private String assetType;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Description")
  private String description;

  @JsonProperty("CIK")
  private String cik;

  @JsonProperty("Exchange")
  private String exchange;

  @JsonProperty("Currency")
  private String currency;

  @JsonProperty("Country")
  private String country;

  @JsonProperty("Sector")
  private String sector;

  @JsonProperty("Industry")
  private String industry;

  @JsonProperty("Address")
  private String address;

  @JsonProperty("FullTimeEmployees")
  private String fullTimeEmployees;

  @JsonProperty("FiscalYearEnd")
  private String fiscalYearEnd;

  @JsonProperty("LatestQuarter")
  private String latestQuarter;

  @JsonProperty("MarketCapitalization")
  private String marketCapitalization;

  @JsonProperty("EBITDA")
  private String ebitda;

  @JsonProperty("PERatio")
  private String peRatio;

  @JsonProperty("PEGRatio")
  private String pegRatio;

  @JsonProperty("BookValue")
  private String bookValue;

  @JsonProperty("DividendPerShare")
  private String dividendPerShare;

  @JsonProperty("DividendYield")
  private String dividendYield;

  @JsonProperty("EPS")
  private String eps;

  @JsonProperty("RevenuePerShareTTM")
  private String revenuePerShareTtm;

  @JsonProperty("ProfitMargin")
  private String profitMargin;

  @JsonProperty("OperatingMarginTTM")
  private String operatingMarginTtm;

  @JsonProperty("ReturnOnAssetsTTM")
  private String returnOnAssetsTtm;

  @JsonProperty("ReturnOnEquityTTM")
  private String returnOnEquityTtm;

  @JsonProperty("RevenueTTM")
  private String revenueTtm;

  @JsonProperty("GrossProfitTTM")
  private String grossProfitTtm;

  @JsonProperty("DilutedEPSTTM")
  private String dilutedEpsttm;

  @JsonProperty("QuarterlyEarningsGrowthYOY")
  private String quarterlyEarningsGrowthYoy;

  @JsonProperty("QuarterlyRevenueGrowthYOY")
  private String quarterlyRevenueGrowthYoy;

  @JsonProperty("AnalystTargetPrice")
  private String analystTargetPrice;

  @JsonProperty("TrailingPE")
  private String trailingPe;

  @JsonProperty("ForwardPE")
  private String forwardPe;

  @JsonProperty("PriceToSalesRatioTTM")
  private String priceToSalesRatioTtm;

  @JsonProperty("PriceToBookRatio")
  private String priceToBookRatio;

  @JsonProperty("EVToRevenue")
  private String evToRevenue;

  @JsonProperty("EVToEBITDA")
  private String evToEbitda;

  @JsonProperty("Beta")
  private String beta;

  @JsonProperty("52WeekHigh")
  private String fiftyTwoWeekHigh;

  @JsonProperty("52WeekLow")
  private String fiftyTwoWeekLow;

  @JsonProperty("50DayMovingAverage")
  private String fiftyDayMovingAverage;

  @JsonProperty("200DayMovingAverage")
  private String twoHundredDayMovingAverage;

  @JsonProperty("SharesOutstanding")
  private String sharesOutstanding;

  @JsonProperty("SharesFloat")
  private String sharesFloat;

  @JsonProperty("SharesShort")
  private String sharesShort;

  @JsonProperty("SharesShortPriorMonth")
  private String sharesShortPriorMonth;

  @JsonProperty("ShortRatio")
  private String shortRatio;

  @JsonProperty("ShortPercentOutstanding")
  private String shortPercentOutstanding;

  @JsonProperty("ShortPercentFloat")
  private String shortPercentFloat;

  @JsonProperty("PercentInsiders")
  private String percentInsiders;

  @JsonProperty("PercentInstitutions")
  private String percentInstitutions;

  @JsonProperty("ForwardAnnualDividendRate")
  private String forwardAnnualDividendRate;

  @JsonProperty("ForwardAnnualDividendYield")
  private String forwardAnnualDividendYield;

  @JsonProperty("PayoutRatio")
  private String payoutRatio;

  @JsonProperty("DividendDate")
  private String dividendDate;

  @JsonProperty("ExDividendDate")
  private String exDividendDate;

  @JsonProperty("LastSplitFactor")
  private String lastSplitFactor;

  @JsonProperty("LastSplitDate")
  private String lastSplitDate;

  @JsonProperty("SourceName")
  private String sourceName;

  @JsonProperty("Type")
  private String type;
}
