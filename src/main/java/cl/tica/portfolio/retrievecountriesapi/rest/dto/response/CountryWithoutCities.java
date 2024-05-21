package cl.tica.portfolio.retrievecountriesapi.rest.dto.response;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Flag;

import java.util.Set;

public record CountryWithoutCities(String name, String capital, String region, String subregion, String flag,
                                   Set<Flag> flags) {
}
