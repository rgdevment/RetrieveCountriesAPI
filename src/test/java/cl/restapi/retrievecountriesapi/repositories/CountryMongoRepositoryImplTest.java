package cl.restapi.retrievecountriesapi.repositories;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.models.CountryTestStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryMongoRepositoryImplTest {
    private CountryMongoRepositoryImpl countryMongoRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        countryMongoRepository = new CountryMongoRepositoryImpl(mongoTemplate);
    }

    @Test
    void shouldRetrieveCountriesWhenExists() {
        List<Country> expectedCountry = CountryTestStub.randomList(5);
        when(mongoTemplate.find(any(Query.class), eq(Country.class))).thenReturn(expectedCountry);

        List<Country> countries = countryMongoRepository.findAllExcludeCities();

        assertEquals(expectedCountry, countries);
    }

    @Test
    void shouldRetrieveCountriesByRegionWhenExists() {
        List<Country> expectedCountry = CountryTestStub.randomList(5);
        when(mongoTemplate.find(any(Query.class), eq(Country.class))).thenReturn(expectedCountry);

        List<Country> countries = countryMongoRepository.findCountriesRegionExcludeCities("Americas");

        assertEquals(expectedCountry, countries);
    }

    @Test
    void shouldRetrieveCountriesBySubregionWhenExists() {
        List<Country> expectedCountry = CountryTestStub.randomList(5);
        when(mongoTemplate.find(any(Query.class), eq(Country.class))).thenReturn(expectedCountry);

        List<Country> countries = countryMongoRepository.findCountriesSubregionExcludeCities("South America");

        assertEquals(expectedCountry, countries);
    }
}
