package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findByName(String name);

    Country findByCapital(String name);

    List<Country> findByRegion(String region);

    List<Country> findBySubregion(String subregion);

}
