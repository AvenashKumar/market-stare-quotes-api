package com.codeofeverything.backendmarketstaresearch.repo;

import com.codeofeverything.backendmarketstaresearch.entities.CompanyOverviewEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyOverviewRepo extends MongoRepository<CompanyOverviewEntity, String> {
  Optional<CompanyOverviewEntity> findCompanyOverviewEntityBySymbol(final String symbol);
}
