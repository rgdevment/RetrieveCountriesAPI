package cl.tica.portfolio.retrievecountriesapi.services;

import cl.tica.portfolio.retrievecountriesapi.documents.Country;
import cl.tica.portfolio.retrievecountriesapi.documents.CountryTestStub;
import cl.tica.portfolio.retrievecountriesapi.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CountryServiceJpaTest {
    private CountryRepository repository;
    private CountryServiceJpa service;

    @BeforeEach
    void setUp() {
        this.repository = mock(CountryRepository.class);
        this.service = new CountryServiceJpa(repository);
    }

    @Test
    void findAll() {
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByName() {
        Country countryExpected = CountryTestStub.random();
        when(repository.findCountriesByNameIgnoreCase(countryExpected.getName())).thenReturn(countryExpected);

        Country country = service.findByName(countryExpected.getName());

        assertEquals(countryExpected.getName(), country.getName());
        assertEquals(countryExpected.getCapital(), country.getCapital());
        assertEquals(countryExpected.getRegion(), country.getRegion());
        assertEquals(countryExpected.getSubregion(), country.getSubregion());
        assertEquals(countryExpected.getCities(), country.getCities());
        assertEquals(countryExpected.getFlag(), country.getFlag());

        verify(repository, times(1)).findCountriesByNameIgnoreCase(countryExpected.getName());
    }

    @Test
    void findByCapital() {
        Country countryExpected = CountryTestStub.random();
        when(repository.findCountriesByCapitalIgnoreCase(countryExpected.getName())).thenReturn(countryExpected);

        Country country = service.findByCapital(countryExpected.getName());

        assertEquals(countryExpected.getName(), country.getName());
        assertEquals(countryExpected.getCapital(), country.getCapital());
        assertEquals(countryExpected.getRegion(), country.getRegion());
        assertEquals(countryExpected.getSubregion(), country.getSubregion());
        assertEquals(countryExpected.getCities(), country.getCities());
        assertEquals(countryExpected.getFlag(), country.getFlag());

        verify(repository, times(1)).findCountriesByCapitalIgnoreCase(countryExpected.getName());
    }

    @Test
    void findByRegion() {
        List<Country> countriesExpected = CountryTestStub.randomList(3);
        when(repository.findCountriesByRegionIgnoreCase(
                countriesExpected.getFirst().getRegion())
        ).thenReturn(countriesExpected);

        List<Country> countries = service.findByRegion(countriesExpected.getFirst().getRegion());
        List<String> cities = countriesExpected.getFirst().getCities();
        List<String> citiesExpected = countries.getFirst().getCities();

        assertEquals(countriesExpected.getFirst().getName(), countries.getFirst().getName());
        assertEquals(countriesExpected.getFirst().getCapital(), countries.getFirst().getCapital());
        assertEquals(countriesExpected.getFirst().getRegion(), countries.getFirst().getRegion());
        assertEquals(countriesExpected.getFirst().getSubregion(), countries.getFirst().getSubregion());
        assertEquals(cities, citiesExpected);
        assertEquals(countriesExpected.getFirst().getFlag(), countries.getFirst().getFlag());

        verify(repository, times(1))
                .findCountriesByRegionIgnoreCase(countriesExpected.getFirst().getRegion());
    }

    @Test
    void findBySubRegion() {
        List<Country> countriesExpected = CountryTestStub.randomList(3);
        when(repository.findCountriesBySubregionIgnoreCase(
                countriesExpected.getFirst().getSubregion())
        ).thenReturn(countriesExpected);

        List<Country> countries = service.findBySubregion(countriesExpected.getFirst().getSubregion());

        assertEquals(countriesExpected.getFirst().getName(), countries.getFirst().getName());
        assertEquals(countriesExpected.getFirst().getCapital(), countries.getFirst().getCapital());
        assertEquals(countriesExpected.getFirst().getRegion(), countries.getFirst().getRegion());
        assertEquals(countriesExpected.getFirst().getSubregion(), countries.getFirst().getSubregion());
        assertEquals(countriesExpected.getFirst().getCities(), countries.getFirst().getCities());
        assertEquals(countriesExpected.getFirst().getFlag(), countries.getFirst().getFlag());

        verify(repository, times(1))
                .findCountriesBySubregionIgnoreCase(countriesExpected.getFirst().getSubregion());
    }
}
