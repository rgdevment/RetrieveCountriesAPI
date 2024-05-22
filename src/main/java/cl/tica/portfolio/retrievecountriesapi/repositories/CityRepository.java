package cl.tica.portfolio.retrievecountriesapi.repositories;

import cl.tica.portfolio.retrievecountriesapi.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
