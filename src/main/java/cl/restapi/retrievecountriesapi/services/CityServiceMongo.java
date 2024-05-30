package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.City;
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
    public List<City> getCitiesByCountryCode(String countryCode) {
        Country country = repository.findByCodeIgnoreCase(countryCode);
        if (country != null) {
            return country.getCities();
        }
        return new ArrayList<>();
    }

    @Override
    public List<City> getCitiesByCountryCodeAndStateCode(String countryCode, String stateCode) {
        Country country = repository.findByCodeIgnoreCase(countryCode);
        if (country != null) {
            List<City> cities = country.getCities();

            return cities.stream().filter(city -> city.stateCode().equalsIgnoreCase(stateCode)).toList();
        }
        return new ArrayList<>();
    }
}
