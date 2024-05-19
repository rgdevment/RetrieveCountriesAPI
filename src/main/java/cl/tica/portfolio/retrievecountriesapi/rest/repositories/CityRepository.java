package cl.tica.portfolio.retrievecountriesapi.rest.repositories;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
