package cl.tica.portfolio.retrievecountriesapi.rest.repositories;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
