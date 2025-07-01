package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;

public record Currency(
        @JsonView(Views.Single.class) String name,
        @JsonView(Views.Single.class) String code,
        @JsonView(Views.Single.class) String symbol
) {
}
