package cl.tica.portfolio.retrievecountriesapi.rest.controllers;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.CountryTestStub;
import cl.tica.portfolio.retrievecountriesapi.rest.services.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {
    @MockBean
    private CountryService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCountries() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findAll()).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].cities").exists())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesWithOutCities() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findAll()).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries?excludeCities=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].cities").doesNotExist())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountryByNameNoContent() throws Exception {
        when(service.findByName("faker")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/faker")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).findByName("faker");
    }

    @Test
    void testGetCountryByName() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByName(country.getName())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/" + country.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.cities").exists())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByName(country.getName());
    }

    @Test
    void testGetCountryByCapital() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByCapital(country.getCapital())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/capital/" + country.getCapital())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.cities").exists())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByCapital(country.getCapital());
    }

    @Test
    void testGetCountryByNameExcludeCities() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByName(country.getName())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/" + country.getName()
                                + "?excludeCities=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.cities").doesNotExist())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByName(country.getName());
    }

    @Test
    void testGetCountryByRegion() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findByRegion(countries.getFirst().getRegion())).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/region/" + countries.getFirst().getRegion())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].cities").exists())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findByRegion(countries.getFirst().getRegion());
    }

    @Test
    void testGetCountryBySubRegion() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findBySubregion(countries.getFirst().getSubregion())).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/countries/subregion/"
                                + countries.getFirst().getSubregion())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].cities").exists())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findBySubregion(countries.getFirst().getSubregion());
    }
}
