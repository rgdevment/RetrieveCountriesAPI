package cl.restapi.retrievecountriesapi.services;

import cl.restapi.retrievecountriesapi.models.State;

import java.util.List;

public interface StateService {
    List<State> getStatesByCountryCode(String countryCode);
}
