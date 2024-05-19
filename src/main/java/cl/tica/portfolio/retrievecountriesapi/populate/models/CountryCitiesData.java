package cl.tica.portfolio.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryCitiesData(List<CountryCities> data) {
}
