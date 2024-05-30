package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.dto.StateResponse;
import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.models.StateTestStub;
import cl.restapi.retrievecountriesapi.services.StateService;
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

@WebMvcTest(StateController.class)
class StateControllerTest {
    @MockBean
    private StateService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatesByCountryCode() throws Exception {
        List<State> states = StateTestStub.randomList(4);
        List<StateResponse> statesResponse = states.stream()
                .map(StateResponse::fromState)
                .toList();

        when(service.getStatesByCountryCode("CL")).thenReturn(statesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/states/country/CL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(statesResponse.getFirst().name()))
                .andExpect(jsonPath("$[0].code").value(statesResponse.getFirst().code()))
                .andExpect(jsonPath("$[0].latitude").value(statesResponse.getFirst().latitude()))
                .andExpect(jsonPath("$[0].longitude").value(statesResponse.getFirst().longitude()));

        verify(service, times(1)).getStatesByCountryCode("CL");
    }

    @Test
    void testGetStatesByCountryCodeEmpty() throws Exception {
        when(service.getStatesByCountryCode("CL")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/states/country/CL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).getStatesByCountryCode("CL");
    }
}
