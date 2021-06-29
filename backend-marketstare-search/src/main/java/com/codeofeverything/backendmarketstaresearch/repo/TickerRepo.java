package com.codeofeverything.backendmarketstaresearch.repo;

import com.codeofeverything.backendmarketstaresearch.entities.TickerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerRepo extends MongoRepository<TickerEntity, String>{

}
