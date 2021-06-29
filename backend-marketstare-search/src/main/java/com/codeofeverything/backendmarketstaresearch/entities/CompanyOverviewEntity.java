package com.codeofeverything.backendmarketstaresearch.entities;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "CompanyOverview")
public class CompanyOverviewEntity {
  @Id
  private String id;

  @Indexed(unique=true)
  private String symbol;

  private String assetType;
  private String name;
  private String description;
  private String cik;
  private String exchange;
  private String currency;
  private String country;
  private String sector;
  private String industry;
  private String address;
  private String fullTimeEmployees;
  private String fiscalYearEnd;
  private String latestQuarter;
  private String marketCapitalization;
  private String ebitda;
  private String peRatio;
  private String pegRatio;
  private String bookValue;
  private String dividendPerShare;
  private String dividendYield;
  private String eps;
  private String revenuePerShareTtm;
  private String profitMargin;
  private String operatingMarginTtm;
  private String returnOnAssetsTtm;
  private String returnOnEquityTtm;
  private String revenueTtm;
  private String grossProfitTtm;
  private String dilutedEpsttm;
  private String quarterlyEarningsGrowthYoy;
  private String quarterlyRevenueGrowthYoy;
  private String analystTargetPrice;
  private String trailingPe;
  private String forwardPe;
  private String priceToSalesRatioTtm;
  private String priceToBookRatio;
  private String evToRevenue;
  private String evToEbitda;
  private String beta;
  private String fiftyTwoWeekHigh;
  private String fiftyTwoWeekLow;
  private String fiftyDayMovingAverage;
  private String twoHundredDayMovingAverage;
  private String sharesOutstanding;
  private String sharesFloat;
  private String sharesShort;
  private String sharesShortPriorMonth;
  private String shortRatio;
  private String shortPercentOutstanding;
  private String shortPercentFloat;
  private String percentInsiders;
  private String percentInstitutions;
  private String forwardAnnualDividendRate;
  private String forwardAnnualDividendYield;
  private String payoutRatio;
  private String dividendDate;
  private String exDividendDate;
  private String lastSplitFactor;
  private String lastSplitDate;
  private String sourceName;
  private String type;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date lastModifiedDate;
}
