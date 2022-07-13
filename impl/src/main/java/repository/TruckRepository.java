package repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import repository.entity.TruckEntity;

@Repository
public interface TruckRepository extends ReactiveMongoRepository<TruckEntity, String> {


}
