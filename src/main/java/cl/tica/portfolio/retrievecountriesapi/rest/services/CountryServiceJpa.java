package cl.tica.portfolio.retrievecountriesapi.rest.services;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.repositories.CountryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Transactional(readOnly = true)
    public List<Country> findAllNoCache() {
        return repository.findAll();
    }

    @CacheEvict(allEntries = true, value = {"countries"})
    @Scheduled(fixedRate = 86400000) // 24 hours
    public void refreshCache() {
        this.findAllNoCache();
    }
}
