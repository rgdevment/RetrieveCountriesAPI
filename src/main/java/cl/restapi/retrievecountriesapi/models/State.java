package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.mongodb.core.mapping.Field;

public record State(
        @JsonView({Views.WithStates.class, Views.Complete.class}) String name,
        @JsonView({Views.Complete.class}) String code,
        @JsonView({Views.Complete.class}) @JsonProperty("country_code") @Field("country_code") String countryCode,
        @JsonView({Views.WithStates.class, Views.Complete.class}) Double latitude,
        @JsonView({Views.WithStates.class, Views.Complete.class}) Double longitude
) {
}
