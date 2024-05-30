package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryCode(String countryCode);

    List<City> getCitiesByCountryCodeAndStateCode(String countryCode, String stateCode);
}
