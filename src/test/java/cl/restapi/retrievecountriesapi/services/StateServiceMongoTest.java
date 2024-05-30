package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.dto.StateResponse;
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
class StateServiceMongoTest {
    private CountryRepository repository;
    private StateServiceMongo service;

    @BeforeEach
    void setUp() {
        this.repository = mock(CountryRepository.class);
        this.service = new StateServiceMongo(repository);
    }

    @Test
    void testGetCitiesByCountryCode() {
        Country country = CountryTestStub.random();
        List<State> statesExcepted = country.getStates();
        when(repository.findByCodeIgnoreCase(country.getCode())).thenReturn(country);

        List<StateResponse> states = service.getStatesByCountryCode(country.getCode());

        assertEquals(statesExcepted.getFirst().name(), states.getFirst().name());
        assertEquals(statesExcepted.getFirst().latitude(), states.getFirst().latitude());
        assertEquals(statesExcepted.getFirst().longitude(), states.getFirst().longitude());

        verify(repository, times(1)).findByCodeIgnoreCase(country.getCode());
    }

    @Test
    void testGetCitiesByCountryCodeEmpty() {
        when(repository.findByCodeIgnoreCase("fake")).thenReturn(null);

        List<StateResponse> states = service.getStatesByCountryCode("fake");

        assertEquals(new ArrayList<>(), states);

        verify(repository, times(1)).findByCodeIgnoreCase("fake");
    }
}
