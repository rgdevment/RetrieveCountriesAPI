package cl.tica.portfolio.retrievecountriesapi.rest.services;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
}
