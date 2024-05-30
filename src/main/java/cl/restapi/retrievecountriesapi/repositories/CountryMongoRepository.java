package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.Country;

import java.util.List;

public interface CountryMongoRepository {
    List<Country> findAllExcludeCities();

    List<Country> findCountriesRegionExcludeCities(String region);

    List<Country> findCountriesSubregionExcludeCities(String subregion);
}
