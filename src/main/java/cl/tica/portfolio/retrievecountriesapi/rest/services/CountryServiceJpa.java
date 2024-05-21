package cl.tica.portfolio.retrievecountriesapi.rest.services;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.repositories.CountryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceJpa implements CountryService {
    private final CountryRepository repository;

    public CountryServiceJpa(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries")
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country findByName(String name) {
        return repository.findCountriesByNameIgnoreCase(name);
    }

    @Override
    public Country findByCapital(String name) {
        return repository.findCountriesByCapitalIgnoreCase(name);
    }

    @Override
    public List<Country> findByRegion(String region) {
        return repository.findCountriesByRegionIgnoreCase(region);
    }

    @Override
    public List<Country> findBySubregion(String subregion) {
        return repository.findCountriesBySubregionIgnoreCase(subregion);
    }
}
