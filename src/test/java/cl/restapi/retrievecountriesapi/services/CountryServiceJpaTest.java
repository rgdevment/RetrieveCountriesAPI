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
        List<City> cities = countriesExpected.getFirst().getCities();
        List<City> citiesExpected = countries.getFirst().getCities();

        List<State> states = countriesExpected.getFirst().getStates();
        List<State> statesExpected = countries.getFirst().getStates();

        assertEquals(countriesExpected.getFirst().getName(), countries.getFirst().getName());
        assertEquals(countriesExpected.getFirst().getCapital(), countries.getFirst().getCapital());
        assertEquals(countriesExpected.getFirst().getRegion(), countries.getFirst().getRegion());
        assertEquals(countriesExpected.getFirst().getSubregion(), countries.getFirst().getSubregion());
        assertEquals(cities.getFirst().getName(), citiesExpected.getFirst().getName());
        assertEquals(cities.getFirst().getCountryCode(), citiesExpected.getFirst().getCountryCode());
        assertEquals(cities.getFirst().getLatitude(), citiesExpected.getFirst().getLatitude());
        assertEquals(cities.getFirst().getLongitude(), citiesExpected.getFirst().getLongitude());
        assertEquals(countriesExpected.getFirst().getFlag(), countries.getFirst().getFlag());

        assertEquals(states.getFirst().getName(), statesExpected.getFirst().getName());
        assertEquals(states.getFirst().getCode(), statesExpected.getFirst().getCode());
        assertEquals(states.getFirst().getCountryCode(), statesExpected.getFirst().getCountryCode());
        assertEquals(states.getFirst().getLatitude(), statesExpected.getFirst().getLatitude());
        assertEquals(states.getFirst().getLongitude(), statesExpected.getFirst().getLongitude());
        assertEquals(states.getFirst().getCities(), statesExpected.getFirst().getCities());

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
