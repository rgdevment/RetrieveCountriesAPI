package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public record City(
        @JsonView({Views.WithCities.class, Views.Complete.class}) String name,
        @JsonView({Views.Complete.class}) @JsonProperty("state_code") String stateCode,
        @JsonProperty("country_code") String countryCode,
        @JsonView({Views.WithCities.class, Views.Complete.class}) Double latitude,
        @JsonView({Views.WithCities.class, Views.Complete.class}) Double longitude
) {
}
