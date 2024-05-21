package cl.tica.portfolio.retrievecountriesapi.rest.services;

import cl.tica.portfolio.retrievecountriesapi.rest.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CacheServiceCaffeineTest {

    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CacheServiceCaffeine cacheServiceCaffeine;

    @Test
    void refreshCacheCallsFindAll() {
        cacheServiceCaffeine.refreshCache();
        verify(repository, times(1)).findAll();
    }
}
