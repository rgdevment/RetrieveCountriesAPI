package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceMongo implements StateService {
    private final CountryRepository repository;

    public StateServiceMongo(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<State> getStatesByCountryCode(String countryCode) {
        Country country = repository.findByCodeIgnoreCase(countryCode);
        if (country != null) {
            return country.getStates();
        }
        return new ArrayList<>();
    }
}
