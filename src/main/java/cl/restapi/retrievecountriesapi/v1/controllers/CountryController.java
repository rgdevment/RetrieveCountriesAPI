package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.Country;
import cl.restapi.retrievecountriesapi.services.CountryService;
import cl.restapi.retrievecountriesapi.v1.Views;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "Countries")
@RestController
@RequestMapping("/v1")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all country data.",
            description = "This operation retrieves data for all countries along with their states. However, you will "
                    + "not be able to obtain a list of cities in this call due to the large number of cities in the "
                    + "world. Including all cities in the response of this API is not recommended for us or for the "
                    + "application consuming this JSON. If you need to obtain cities, you can make a new call to one "
                    + "of our other endpoints or filter by country, region, or other criteria. States are included by "
                    + "default, but you can exclude them using the parameter excludeStates=true. "
                    + "As a commitment to best practices, we ask for your help by caching the API response to avoid "
                    + "excessive usage and help us keep it public and available for everyone."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class))
    )
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<MappingJacksonValue> getCountries(
            @RequestParam(required = false) Boolean excludeStates) {
        List<Country> countries = service.findAll();
        return getMappingJacksonValueResponseListEntity(excludeStates, countries);
    }

    @Operation(summary = "Get country data by name.",
            description = "This operation retrieves data for a country by its name and includes its cities. "
                    + "If you wish to include the states from the results, you can use the parameter "
                    + "excludeStates=false; similarly, if you want to exclude the cities, you can use "
                    + "excludeCities=true; "
                    + "As a commitment to best practices, we ask for your help by caching the API response to avoid "
                    + "excessive usage and help us keep it public and available for everyone."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByName(
            @PathVariable @Parameter(example = "Chile") String name,
            @RequestParam(required = false) Boolean excludeCities,
            @RequestParam(required = false, defaultValue = "true") Boolean excludeStates) {
        Country country = this.service.findByName(name);
        return getMappingJacksonValueResponseEntity(excludeCities, excludeStates, country);
    }

    @Operation(summary = "Get country data by capital.",
            description = "This operation retrieves data for a country by its capital and includes its cities."
                    + "If you wish to include the states from the results, you can use the parameter "
                    + "excludeStates=false; similarly, if you want to exclude the cities, you can use "
                    + "excludeCities=true; "
                    + "As a commitment to best practices, we ask for your help by caching the API response to avoid "
                    + "excessive usage and help us keep it public and available for everyone."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/capital/{name}")
    public ResponseEntity<MappingJacksonValue> getCountryByCapital(
            @PathVariable @Parameter(example = "Santiago") String name,
            @RequestParam(required = false) Boolean excludeCities,
            @RequestParam(required = false, defaultValue = "true") Boolean excludeStates) {
        Country country = this.service.findByCapital(name);
        return getMappingJacksonValueResponseEntity(excludeCities, excludeStates, country);
    }

    @Operation(summary = "Get country data by region.",
            description = "This operation retrieves data for countries by its region and includes its states. "
                    + "If you wish to exclude the states from the results, you can use the parameter "
                    + "excludeStates=true; "
                    + "As a commitment to best practices, we ask for your help by caching the API response to avoid "
                    + "excessive usage and help us keep it public and available for everyone."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/region/{region}")
    public ResponseEntity<MappingJacksonValue> getCountriesByRegion(
            @PathVariable @Parameter(example = "Americas") String region,
            @RequestParam(required = false) Boolean excludeStates) {
        List<Country> countries = service.findByRegion(region);
        return getMappingJacksonValueResponseListEntity(excludeStates, countries);
    }

    @Operation(summary = "Get country data by subregion.",
            description = "This operation retrieves data for countries by its subregion and includes its states. "
                    + "If you wish to exclude the states from the results, you can use the parameter "
                    + "excludeStates=true; "
                    + "As a commitment to best practices, we ask for your help by caching the API response to avoid "
                    + "excessive usage and help us keep it public and available for everyone."
    )
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/subregion/{subregion}")
    public ResponseEntity<MappingJacksonValue> getCountriesBySubregion(
            @PathVariable @Parameter(example = "South America") String subregion,
            @RequestParam(required = false) Boolean excludeStates) {
        List<Country> countries = service.findBySubregion(subregion);
        return getMappingJacksonValueResponseListEntity(excludeStates, countries);
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseListEntity(
            Boolean excludeStates,
            List<Country> countries
    ) {
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(countries);
            return getViewMappingJacksonValueResponseEntity(true, excludeStates, mappingJacksonValue);
        }
    }

    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseEntity(
            Boolean excludeCities,
            Boolean excludeStates,
            Country country) {
        if (country == null) {
            return ResponseEntity.noContent().build();
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(country);
            return getViewMappingJacksonValueResponseEntity(excludeCities, excludeStates, mappingJacksonValue);
        }
    }

    private static ResponseEntity<MappingJacksonValue> getViewMappingJacksonValueResponseEntity(
            Boolean excludeCities,
            Boolean excludeStates,
            MappingJacksonValue mappingJacksonValue
    ) {
        if (Boolean.TRUE.equals(excludeCities) && Boolean.TRUE.equals(excludeStates)) {
            mappingJacksonValue.setSerializationView(Views.Single.class);
        } else if (Boolean.TRUE.equals(excludeCities)) {
            mappingJacksonValue.setSerializationView(Views.WithStates.class);
        } else if (Boolean.TRUE.equals(excludeStates)) {
            mappingJacksonValue.setSerializationView(Views.WithCities.class);
        } else {
            mappingJacksonValue.setSerializationView(Views.Complete.class);
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(mappingJacksonValue);
    }
}
