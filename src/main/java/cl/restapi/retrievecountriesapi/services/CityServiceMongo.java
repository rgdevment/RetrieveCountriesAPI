package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.dto.CityResponse;
import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceMongo implements CityService {
    private final CountryRepository repository;

    public CityServiceMongo(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CityResponse> getCitiesByCountryCode(String countryCode) {
        Country country = repository.findByCodeIgnoreCase(countryCode);
        if (country == null) {
            return new ArrayList<>();
        }

        return country.getCities().stream()
                .map(CityResponse::fromCity)
                .toList();
    }

    @Override
    public List<CityResponse> getCitiesByCountryCodeAndStateCode(String countryCode, String stateCode) {
        Country country = repository.findByCodeIgnoreCase(countryCode);
        if (country == null) {
            return new ArrayList<>();
        }

        return country.getCities().stream()
                .filter(city -> city.stateCode().equalsIgnoreCase(stateCode))
                .map(CityResponse::fromCity)
                .toList();
    }
}
