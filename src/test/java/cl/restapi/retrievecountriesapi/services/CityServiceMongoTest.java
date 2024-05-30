package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.dto.CityResponse;
import cl.restapi.retrievecountriesapi.models.City;
import cl.restapi.retrievecountriesapi.models.CityTestStub;
import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.models.CountryTestStub;
import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityServiceMongoTest {
    private CountryRepository repository;
    private CityServiceMongo service;

    @BeforeEach
    void setUp() {
        this.repository = mock(CountryRepository.class);
        this.service = new CityServiceMongo(repository);
    }

    @Test
    void testGetCitiesByCountryCode() {
        Country country = CountryTestStub.random();
        List<City> citiesExcepted = country.getCities();
        when(repository.findByCodeIgnoreCase(country.getCode())).thenReturn(country);

        List<CityResponse> cities = service.getCitiesByCountryCode(country.getCode());

        assertEquals(citiesExcepted.getFirst().name(), cities.getFirst().name());
        assertEquals(citiesExcepted.getFirst().latitude(), cities.getFirst().latitude());
        assertEquals(citiesExcepted.getFirst().longitude(), cities.getFirst().longitude());

        verify(repository, times(1)).findByCodeIgnoreCase(country.getCode());
    }

    @Test
    void testGetCitiesByCountryCodeEmpty() {
        when(repository.findByCodeIgnoreCase("fake")).thenReturn(null);

        List<CityResponse> cities = service.getCitiesByCountryCode("fake");

        assertEquals(new ArrayList<>(), cities);

        verify(repository, times(1)).findByCodeIgnoreCase("fake");
    }

    @Test
    void testGetCitiesByCountryCodeAndStateCodeEmpty() {
        when(repository.findByCodeIgnoreCase("fake")).thenReturn(null);

        List<CityResponse> cities =
                service.getCitiesByCountryCodeAndStateCode("fake", "fake");

        assertEquals(new ArrayList<>(), cities);

        verify(repository, times(1)).findByCodeIgnoreCase("fake");
    }

    @Test
    void testGetCitiesByCountryCodeAndStateCode() {
        Country country = CountryTestStub.random();

        State state = country.getStates().getFirst();
        City city = CityTestStub.create("city", state.code(), country.getCode(), 0.0, 0.0);
        country.getCities().add(city);

        when(repository.findByCodeIgnoreCase(country.getCode())).thenReturn(country);

        List<CityResponse> cities =
                service.getCitiesByCountryCodeAndStateCode(country.getCode(), country.getStates().getFirst().code());

        assertEquals(city.name(), cities.getFirst().name());
        assertEquals(city.latitude(), cities.getFirst().latitude());
        assertEquals(city.longitude(), cities.getFirst().longitude());

        verify(repository, times(1)).findByCodeIgnoreCase(country.getCode());
    }

}
