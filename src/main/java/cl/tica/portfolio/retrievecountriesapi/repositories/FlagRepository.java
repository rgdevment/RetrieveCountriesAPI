package cl.tica.portfolio.retrievecountriesapi.repositories;

import cl.tica.portfolio.retrievecountriesapi.documents.Flag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlagRepository extends MongoRepository<Flag, String> {
}
