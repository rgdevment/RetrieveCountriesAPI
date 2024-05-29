package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.services.CountryService;
import cl.restapi.retrievecountriesapi.v1.Views;
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
@RequestMapping("/v1")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all country data with options to exclude cities and/or states.",
            description = "This operation retrieves all countries from the database. If includeCities is true, the "
                    + "cities will be included. If includeStates is true, the states will be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<MappingJacksonValue> getCountries(@RequestParam(required = false) Boolean includeCities,
                                                            @RequestParam(required = false) Boolean includeStates) {
        List<Country> countries = service.findAll();
        return getMappingJacksonValueResponseListEntity(includeCities, includeStates, countries);
    }

    @Operation(summary = "Get country data by name.",
            description = "This operation retrieves a country from the database by its name. If includeCities is true, "
                    + "the cities will be included. If includeStates is true, the states will be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByName(
            @PathVariable String name,
            @RequestParam(required = false, defaultValue = "true") Boolean includeCities,
            @RequestParam(required = false) Boolean includeStates) {
        Country country = this.service.findByName(name);
        return getMappingJacksonValueResponseEntity(includeCities, includeStates, country);
    }

    @Operation(summary = "Get country data by capital.",
            description = "This operation retrieves a country from the database by its capital. If includeCities is "
                    + "true, the cities will be included. If includeStates is true, the states will be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/capital/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByCapital(
            @PathVariable String name,
            @RequestParam(required = false, defaultValue = "true") Boolean includeCities,
            @RequestParam(required = false) Boolean includeStates) {
        Country country = this.service.findByCapital(name);
        return getMappingJacksonValueResponseEntity(includeCities, includeStates, country);
    }

    @Operation(summary = "Get countries by region.",
            description = "This operation retrieves countries from the database by region. If includeCities is true, "
                    + "the cities will be included. If includeStates is true, the states will be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/region/{region}")
    public ResponseEntity<MappingJacksonValue> getCountriesByRegion(
            @PathVariable String region,
            @RequestParam(required = false) Boolean includeCities,
            @RequestParam(required = false, defaultValue = "true") Boolean includeStates) {
        List<Country> countries = service.findByRegion(region);
        return getMappingJacksonValueResponseListEntity(includeCities, includeStates, countries);
    }

    @Operation(summary = "Get countries by subregion.",
            description = "This operation retrieves countries from the database by subregion. If includeCities is true,"
                    + " the cities will be included. If includeStates is true, the states will be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/subregion/{subregion}")
    public ResponseEntity<MappingJacksonValue> getCountriesBySubregion(
            @PathVariable String subregion,
            @RequestParam(required = false) Boolean includeCities,
            @RequestParam(required = false, defaultValue = "true") Boolean includeStates) {
        List<Country> countries = service.findBySubregion(subregion);
        return getMappingJacksonValueResponseListEntity(includeCities, includeStates, countries);
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseListEntity(
            Boolean includeCities,
            Boolean includeStates,
            List<Country> countries
    ) {
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(countries);
            return getViewMappingJacksonValueResponseEntity(includeCities, includeStates, mappingJacksonValue);
        }
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseEntity(
            Boolean includeCities,
            Boolean includeStates,
            Country country) {
        if (country == null) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(country);
            return getViewMappingJacksonValueResponseEntity(includeCities, includeStates, mappingJacksonValue);
        }
    }

    private static ResponseEntity<MappingJacksonValue> getViewMappingJacksonValueResponseEntity(
            Boolean includeCities,
            Boolean includeStates,
            MappingJacksonValue mappingJacksonValue
    ) {
        if (Boolean.TRUE.equals(includeCities) && Boolean.TRUE.equals(includeStates)) {
            mappingJacksonValue.setSerializationView(Views.Complete.class);
        } else if (Boolean.TRUE.equals(includeCities)) {
            mappingJacksonValue.setSerializationView(Views.WithCities.class);
        } else if (Boolean.TRUE.equals(includeStates)) {
            mappingJacksonValue.setSerializationView(Views.WithStates.class);
        } else {
            mappingJacksonValue.setSerializationView(Views.Single.class);
        }
        return ResponseEntity.ok(mappingJacksonValue);
    }
}
