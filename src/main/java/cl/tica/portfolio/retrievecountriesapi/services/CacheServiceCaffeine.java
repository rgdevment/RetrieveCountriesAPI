package cl.tica.portfolio.retrievecountriesapi.services;

import cl.tica.portfolio.retrievecountriesapi.repositories.CountryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CacheServiceCaffeine {
    private final CountryRepository repository;

    public CacheServiceCaffeine(CountryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @CacheEvict(allEntries = true, value = {"countries"})
    @Scheduled(fixedRate = 3600000 * 20) // 24 hours
    public void refreshCache() {
        repository.findAll();
    }
}
