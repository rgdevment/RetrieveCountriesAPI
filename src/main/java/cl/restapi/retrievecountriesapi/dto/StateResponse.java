package cl.restapi.retrievecountriesapi.dto;

import cl.restapi.retrievecountriesapi.models.State;

public record StateResponse(String name, String code, Double latitude, Double longitude) {
    public static StateResponse fromState(State state) {
        return new StateResponse(state.name(), state.code(), state.latitude(), state.longitude());
    }
}
