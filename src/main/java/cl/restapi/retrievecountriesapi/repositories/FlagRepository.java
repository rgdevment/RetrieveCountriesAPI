package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.documents.Flag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlagRepository extends MongoRepository<Flag, String> {
}
