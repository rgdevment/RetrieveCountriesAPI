package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.dto.CityResponse;

import java.util.List;

public interface CityService {
    List<CityResponse> getCitiesByCountryCode(String countryCode);

    List<CityResponse> getCitiesByCountryCodeAndStateCode(String countryCode, String stateCode);
}
