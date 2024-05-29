package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;

public record Flag(
        @JsonView(Views.Single.class) String ico,
        @JsonView(Views.Single.class) String png,
        @JsonView(Views.Single.class) String svg,
        @JsonView(Views.Single.class) String alt
) {
}
