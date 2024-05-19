package cl.tica.portfolio.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Flags(String png, String svg) {
}
