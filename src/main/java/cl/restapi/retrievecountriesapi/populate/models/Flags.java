package cl.restapi.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Flags(String png, String svg, String alt) {
}
