package cl.restapi.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountriesWrapper(List<CountryData> countries) {
}
