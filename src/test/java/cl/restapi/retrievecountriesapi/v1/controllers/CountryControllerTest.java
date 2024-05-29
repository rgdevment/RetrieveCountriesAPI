package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.models.CountryTestStub;
import cl.restapi.retrievecountriesapi.services.CountryService;
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

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").doesNotExist())
                .andExpect(jsonPath("$[0].states").doesNotExist())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesWithCities() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findAll()).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/all?includeCities=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").exists())
                .andExpect(jsonPath("$[0].states").doesNotExist())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesWithStates() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findAll()).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/all?includeStates=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").doesNotExist())
                .andExpect(jsonPath("$[0].states").exists())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesWithCitiesAndStates() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findAll()).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/all?includeCities=true&includeStates=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").exists())
                .andExpect(jsonPath("$[0].states").exists())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountriesNoContent() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).findAll();
    }

    @Test
    void testGetCountryByNameNoContent() throws Exception {
        when(service.findByName("faker")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/faker")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).findByName("faker");
    }

    @Test
    void testGetCountryByName() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByName(country.getName())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/" + country.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.iso2").value(country.getIso2()))
                .andExpect(jsonPath("$.iso3").value(country.getIso3()))
                .andExpect(jsonPath("$.tld").value(country.getTld()))
                .andExpect(jsonPath("$.phoneCode").value(country.getPhoneCode()))
                .andExpect(jsonPath("$.latitude").value(country.getLatitude()))
                .andExpect(jsonPath("$.longitude").value(country.getLongitude()))
                .andExpect(jsonPath("$.currency").value(country.getCurrency()))
                .andExpect(jsonPath("$.cities").doesNotExist())
                .andExpect(jsonPath("$.states").doesNotExist())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByName(country.getName());
    }

    @Test
    void testGetCountryByCapital() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByCapital(country.getCapital())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/capital/" + country.getCapital())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.iso2").value(country.getIso2()))
                .andExpect(jsonPath("$.iso3").value(country.getIso3()))
                .andExpect(jsonPath("$.tld").value(country.getTld()))
                .andExpect(jsonPath("$.phoneCode").value(country.getPhoneCode()))
                .andExpect(jsonPath("$.latitude").value(country.getLatitude()))
                .andExpect(jsonPath("$.longitude").value(country.getLongitude()))
                .andExpect(jsonPath("$.currency").value(country.getCurrency()))
                .andExpect(jsonPath("$.cities").doesNotExist())
                .andExpect(jsonPath("$.states").doesNotExist())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByCapital(country.getCapital());
    }

    @Test
    void testGetCountryByNameExcludeCities() throws Exception {
        Country country = CountryTestStub.random();

        when(service.findByName(country.getName())).thenReturn(country);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/" + country.getName()
                                + "?includeCities=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(country.getName()))
                .andExpect(jsonPath("$.capital").value(country.getCapital()))
                .andExpect(jsonPath("$.region").value(country.getRegion()))
                .andExpect(jsonPath("$.subregion").value(country.getSubregion()))
                .andExpect(jsonPath("$.iso2").value(country.getIso2()))
                .andExpect(jsonPath("$.iso3").value(country.getIso3()))
                .andExpect(jsonPath("$.tld").value(country.getTld()))
                .andExpect(jsonPath("$.phoneCode").value(country.getPhoneCode()))
                .andExpect(jsonPath("$.latitude").value(country.getLatitude()))
                .andExpect(jsonPath("$.longitude").value(country.getLongitude()))
                .andExpect(jsonPath("$.currency").value(country.getCurrency()))
                .andExpect(jsonPath("$.cities").exists())
                .andExpect(jsonPath("$.states").doesNotExist())
                .andExpect(jsonPath("$.flag").exists());

        verify(service, times(1)).findByName(country.getName());
    }

    @Test
    void testGetCountryByRegion() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findByRegion(countries.getFirst().getRegion())).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/region/" + countries.getFirst().getRegion())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").doesNotExist())
                .andExpect(jsonPath("$[0].states").doesNotExist())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findByRegion(countries.getFirst().getRegion());
    }

    @Test
    void testGetCountryBySubRegion() throws Exception {
        List<Country> countries = CountryTestStub.randomList(4);

        when(service.findBySubregion(countries.getFirst().getSubregion())).thenReturn(countries);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/subregion/"
                                + countries.getFirst().getSubregion())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(countries.getFirst().getName()))
                .andExpect(jsonPath("$[0].capital").value(countries.getFirst().getCapital()))
                .andExpect(jsonPath("$[0].region").value(countries.getFirst().getRegion()))
                .andExpect(jsonPath("$[0].subregion").value(countries.getFirst().getSubregion()))
                .andExpect(jsonPath("$[0].iso2").value(countries.getFirst().getIso2()))
                .andExpect(jsonPath("$[0].iso3").value(countries.getFirst().getIso3()))
                .andExpect(jsonPath("$[0].tld").value(countries.getFirst().getTld()))
                .andExpect(jsonPath("$[0].phoneCode").value(countries.getFirst().getPhoneCode()))
                .andExpect(jsonPath("$[0].latitude").value(countries.getFirst().getLatitude()))
                .andExpect(jsonPath("$[0].longitude").value(countries.getFirst().getLongitude()))
                .andExpect(jsonPath("$[0].currency").value(countries.getFirst().getCurrency()))
                .andExpect(jsonPath("$[0].cities").doesNotExist())
                .andExpect(jsonPath("$[0].states").doesNotExist())
                .andExpect(jsonPath("$[0].flag").exists());

        verify(service, times(1)).findBySubregion(countries.getFirst().getSubregion());
    }
}
