package com.forttiori.multimodulereactivewebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.forttiori.multimodulereactivewebflux.repository.entity.TruckEntity;

@Repository
public interface TruckRepository extends ReactiveMongoRepository<TruckEntity, String> {


}
