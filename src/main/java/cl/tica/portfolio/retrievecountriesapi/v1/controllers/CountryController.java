package cl.tica.portfolio.retrievecountriesapi.v1.controllers;

import cl.tica.portfolio.retrievecountriesapi.v1.Views;
import cl.tica.portfolio.retrievecountriesapi.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Countries")
@RestController
@RequestMapping("/v1/countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all country data with an option to exclude cities.",
            description = "This operation retrieves all countries from the database. If excludeCities is true, the "
                    + "cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<MappingJacksonValue> getCountries(@RequestParam(required = false) Boolean excludeCities) {
        List<Country> countries = service.findAll();
        return getMappingJacksonValueResponseListEntity(excludeCities, countries);
    }

    @Operation(summary = "Get country data by name.",
            description = "This operation retrieves a country from the database by its name. If excludeCities is true, "
                    + "the cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByName(@PathVariable String name,
                                                                @RequestParam(required = false) Boolean excludeCities) {
        Country country = this.service.findByName(name);
        return getMappingJacksonValueResponseEntity(excludeCities, country);
    }

    @Operation(summary = "Get country data by capital.",
            description = "This operation retrieves a country from the database by its capital. If excludeCities is "
                    + "true, the cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/capital/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByCapital(
            @PathVariable String name, @RequestParam(required = false) Boolean excludeCities) {
        Country country = this.service.findByCapital(name);
        return getMappingJacksonValueResponseEntity(excludeCities, country);
    }

    @Operation(summary = "Get countries by region.",
            description = "This operation retrieves countries from the database by region. If excludeCities is true, "
                    + "the cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/region/{region}")
    public ResponseEntity<MappingJacksonValue> getCountriesByRegion(
            @PathVariable String region, @RequestParam(required = false) Boolean excludeCities) {
        List<Country> countries = service.findByRegion(region);
        return getMappingJacksonValueResponseListEntity(excludeCities, countries);
    }

    @Operation(summary = "Get countries by subregion.",
            description = "This operation retrieves countries from the database by subregion. If excludeCities is true,"
                    + " the cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/subregion/{subregion}")
    public ResponseEntity<MappingJacksonValue> getCountriesBySubregion(
            @PathVariable String subregion, @RequestParam(required = false) Boolean excludeCities) {
        List<Country> countries = service.findBySubregion(subregion);
        return getMappingJacksonValueResponseListEntity(excludeCities, countries);
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseListEntity(
            @RequestParam(required = false) Boolean excludeCities,
            List<Country> countries) {
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(countries);
            if (Boolean.TRUE.equals(excludeCities)) {
                mappingJacksonValue.setSerializationView(Views.Single.class);
            } else {
                mappingJacksonValue.setSerializationView(Views.Complete.class);
            }
            return ResponseEntity.ok(mappingJacksonValue);
        }
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseEntity(
            @RequestParam(required = false) Boolean excludeCities,
            Country country) {
        if (country == null) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(country);
            if (Boolean.TRUE.equals(excludeCities)) {
                mappingJacksonValue.setSerializationView(Views.Single.class);
            } else {
                mappingJacksonValue.setSerializationView(Views.Complete.class);
            }
            return ResponseEntity.ok(mappingJacksonValue);
        }
    }
}
