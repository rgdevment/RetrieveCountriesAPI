package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceMongo implements CountryService {
    private final CountryRepository repository;

    public CountryServiceMongo(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_all")
    public List<Country> findAll() {
        return repository.findAllExcludeCities();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(String name) {
        return repository.findCountriesByNameIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByCapital(String name) {
        return repository.findCountriesByCapitalIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_region")
    public List<Country> findByRegion(String region) {
        return repository.findCountriesRegionExcludeCities(region);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_subregion")
    public List<Country> findBySubregion(String subregion) {
        return repository.findCountriesSubregionExcludeCities(subregion);
    }
}
