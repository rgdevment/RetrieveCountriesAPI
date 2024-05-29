package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.City;
import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.models.CountryTestStub;
import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CountryServiceMongoTest {
    private CountryRepository repository;
    private CountryServiceMongo service;

    @BeforeEach
    void setUp() {
        this.repository = mock(CountryRepository.class);
        this.service = new CountryServiceMongo(repository);
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
        assertEquals(countryExpected.getFlags(), country.getFlags());

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
        assertEquals(countryExpected.getFlags(), country.getFlags());

        verify(repository, times(1)).findCountriesByCapitalIgnoreCase(countryExpected.getName());
    }

    @Test
    void findByRegion() {
        List<Country> countriesExpected = CountryTestStub.randomList(3);
        when(repository.findCountriesByRegionIgnoreCase(
                countriesExpected.getFirst().getRegion())
        ).thenReturn(countriesExpected);

        List<Country> countries = service.findByRegion(countriesExpected.getFirst().getRegion());
        List<City> cities = countriesExpected.getFirst().getCities();
        List<City> citiesExpected = countries.getFirst().getCities();

        List<State> states = countriesExpected.getFirst().getStates();
        List<State> statesExpected = countries.getFirst().getStates();

        assertEquals(countriesExpected.getFirst().getName(), countries.getFirst().getName());
        assertEquals(countriesExpected.getFirst().getCapital(), countries.getFirst().getCapital());
        assertEquals(countriesExpected.getFirst().getRegion(), countries.getFirst().getRegion());
        assertEquals(countriesExpected.getFirst().getSubregion(), countries.getFirst().getSubregion());
        assertEquals(cities.getFirst().name(), citiesExpected.getFirst().name());
        assertEquals(cities.getFirst().countryCode(), citiesExpected.getFirst().countryCode());
        assertEquals(cities.getFirst().latitude(), citiesExpected.getFirst().latitude());
        assertEquals(cities.getFirst().longitude(), citiesExpected.getFirst().longitude());
        assertEquals(countriesExpected.getFirst().getFlags(), countries.getFirst().getFlags());

        assertEquals(states.getFirst().name(), statesExpected.getFirst().name());
        assertEquals(states.getFirst().code(), statesExpected.getFirst().code());
        assertEquals(states.getFirst().countryCode(), statesExpected.getFirst().countryCode());
        assertEquals(states.getFirst().latitude(), statesExpected.getFirst().latitude());
        assertEquals(states.getFirst().longitude(), statesExpected.getFirst().longitude());

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
        assertEquals(countriesExpected.getFirst().getFlags(), countries.getFirst().getFlags());

        verify(repository, times(1))
                .findCountriesBySubregionIgnoreCase(countriesExpected.getFirst().getSubregion());
    }
}
