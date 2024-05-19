package cl.tica.portfolio.retrievecountriesapi.rest.repositories;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, Long> {
}
