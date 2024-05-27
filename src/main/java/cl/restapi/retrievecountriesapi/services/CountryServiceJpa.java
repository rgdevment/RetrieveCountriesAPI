package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.documents.Country;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
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
    @Transactional(readOnly = true)
    @Cacheable("countries_by_name")
    public Country findByName(String name) {
        return repository.findCountriesByNameIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_by_capital")
    public Country findByCapital(String name) {
        return repository.findCountriesByCapitalIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_by_region")
    public List<Country> findByRegion(String region) {
        return repository.findCountriesByRegionIgnoreCase(region);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("countries_by_subregion")
    public List<Country> findBySubregion(String subregion) {
        return repository.findCountriesBySubregionIgnoreCase(subregion);
    }
}
