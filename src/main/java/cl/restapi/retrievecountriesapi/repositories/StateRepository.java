package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {
}
