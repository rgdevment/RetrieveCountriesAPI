package cl.tica.portfolio.retrievecountriesapi.rest.controllers;

import cl.tica.portfolio.retrievecountriesapi.rest.dto.response.CountryWithoutCities;
import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
            description = "This operation retrieves all countries from the database. If excludeCities is true, the cities will not be included.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<Object> getCountries(@RequestParam(required = false) Boolean excludeCities) {
        if (Boolean.TRUE.equals(excludeCities)) {
            List<CountryWithoutCities> countries = this.service.findAllExcludeCities();
            if (countries.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(countries);
            }
        } else {
            List<Country> countries = this.service.findAll();
            if (countries.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(countries);
            }
        }
    }

    @Operation(summary = "Get country data by name.",
            description = "This operation retrieves a country from the database by its name.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        Country country = this.service.findByName(name);
        if (country == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @Operation(summary = "Get country data by capital.",
            description = "This operation retrieves a country from the database by its capital.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/capital/{name}")
    public ResponseEntity<Country> getCountryByCapital(@PathVariable String name) {
        Country country = this.service.findByCapital(name);
        if (country == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @Operation(summary = "Get countries by region.",
            description = "This operation retrieves countries from the database by region.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/region/{region}")
    public ResponseEntity<List<Country>> getCountriesByRegion(@PathVariable String region) {
        List<Country> countries = this.service.findByRegion(region);
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(countries);
        }
    }

    @Operation(summary = "Get countries by subregion.",
            description = "This operation retrieves countries from the database by subregion.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping("/subregion/{subregion}")
    public ResponseEntity<List<Country>> getCountriesBySubregion(@PathVariable String subregion) {
        List<Country> countries = this.service.findBySubregion(subregion);
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(countries);
        }
    }
}
