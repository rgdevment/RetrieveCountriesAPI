package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
