package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.dto.CityResponse;
import cl.restapi.retrievecountriesapi.models.City;
import cl.restapi.retrievecountriesapi.models.CityTestStub;
import cl.restapi.retrievecountriesapi.services.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {
    @MockBean
    private CityService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCitiesByCountryCode() throws Exception {
        List<City> cities = CityTestStub.randomList(4);
        List<CityResponse> citiesResponse = cities.stream()
                .map(CityResponse::fromCity)
                .toList();

        when(service.getCitiesByCountryCode("CL")).thenReturn(citiesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cities/country/CL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(citiesResponse.getFirst().name()))
                .andExpect(jsonPath("$[0].latitude").value(citiesResponse.getFirst().latitude()))
                .andExpect(jsonPath("$[0].longitude").value(citiesResponse.getFirst().longitude()));

        verify(service, times(1)).getCitiesByCountryCode("CL");
    }

    @Test
    void testGetCitiesByCountryCodeEmpty() throws Exception {
        when(service.getCitiesByCountryCode("CL")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cities/country/CL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).getCitiesByCountryCode("CL");
    }

    @Test
    void testGetCitiesByCountryCodeAndStateCode() throws Exception {
        List<City> cities = CityTestStub.randomList(4);
        List<CityResponse> citiesResponse = cities.stream()
                .map(CityResponse::fromCity)
                .toList();

        when(service.getCitiesByCountryCodeAndStateCode("CL", "AN")).thenReturn(citiesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cities/country/CL/state/AN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(citiesResponse.getFirst().name()))
                .andExpect(jsonPath("$[0].latitude").value(citiesResponse.getFirst().latitude()))
                .andExpect(jsonPath("$[0].longitude").value(citiesResponse.getFirst().longitude()));

        verify(service, times(1)).getCitiesByCountryCodeAndStateCode("CL", "AN");
    }
}
