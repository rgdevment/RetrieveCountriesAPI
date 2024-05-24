package cl.tica.portfolio.retrievecountriesapi.repositories;

import cl.tica.portfolio.retrievecountriesapi.documents.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, String> {
    Country findCountriesByNameIgnoreCase(String name);

    Country findCountriesByCapitalIgnoreCase(String capital);

    List<Country> findCountriesByRegionIgnoreCase(String region);

    List<Country> findCountriesBySubregionIgnoreCase(String subregion);
}
