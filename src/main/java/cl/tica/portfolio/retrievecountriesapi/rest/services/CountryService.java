package cl.tica.portfolio.retrievecountriesapi.rest.services;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findByName(String name);

    Country findByCapital(String name);

    List<Country> findByRegion(String region);

    List<Country> findBySubregion(String subregion);

}
