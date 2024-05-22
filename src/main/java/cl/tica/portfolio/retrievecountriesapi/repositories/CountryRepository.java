package cl.tica.portfolio.retrievecountriesapi.repositories;

import cl.tica.portfolio.retrievecountriesapi.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountriesByNameIgnoreCase(String name);

    Country findCountriesByCapitalIgnoreCase(String capital);

    List<Country> findCountriesByRegionIgnoreCase(String region);

    List<Country> findCountriesBySubregionIgnoreCase(String subregion);
}
