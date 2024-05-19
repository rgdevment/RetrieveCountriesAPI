package cl.tica.portfolio.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryData(Name name, List<String> capital, String region, String subregion, String flag, Flags flags) {
}
