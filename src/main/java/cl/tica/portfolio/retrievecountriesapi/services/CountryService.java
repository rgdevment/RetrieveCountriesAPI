package cl.tica.portfolio.retrievecountriesapi.services;

import cl.tica.portfolio.retrievecountriesapi.entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findByName(String name);

    Country findByCapital(String name);

    List<Country> findByRegion(String region);

    List<Country> findBySubregion(String subregion);

}
