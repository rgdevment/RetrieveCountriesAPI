package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.dto.StateResponse;

import java.util.List;

public interface StateService {
    List<StateResponse> getStatesByCountryCode(String countryCode);
}
