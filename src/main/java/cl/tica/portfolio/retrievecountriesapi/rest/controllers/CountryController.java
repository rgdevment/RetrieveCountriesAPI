package cl.tica.portfolio.retrievecountriesapi.rest.controllers;

import cl.tica.portfolio.retrievecountriesapi.rest.entities.Country;
import cl.tica.portfolio.retrievecountriesapi.rest.services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Operation(summary = "Get all country data.",
            description = "This operation retrieves all countries from the database.")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)))
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = this.service.findAll();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(countries);
        }
    }
}
