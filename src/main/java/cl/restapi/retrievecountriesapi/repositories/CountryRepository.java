package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, String>, CountryMongoRepository {
    List<Country> findAllExcludeCities();

    Country findCountriesByNameIgnoreCase(String name);

    Country findCountriesByCapitalIgnoreCase(String capital);

    List<Country> findCountriesRegionExcludeCities(String region);

    List<Country> findCountriesSubregionExcludeCities(String subregion);

    Country findByCodeIgnoreCase(String code);
}
