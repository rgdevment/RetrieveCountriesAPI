package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;

public record State(
        @JsonView({Views.WithStates.class, Views.Complete.class}) String name,
        @JsonView({Views.Complete.class}) String code,
        @Schema(hidden = true) String countryCode,
        @JsonView({Views.WithStates.class, Views.Complete.class}) Double latitude,
        @JsonView({Views.WithStates.class, Views.Complete.class}) Double longitude
) {
}
