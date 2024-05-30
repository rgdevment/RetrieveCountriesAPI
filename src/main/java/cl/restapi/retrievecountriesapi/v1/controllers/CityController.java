package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.City;
import cl.restapi.retrievecountriesapi.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "Cities")
@RestController
@RequestMapping("/v1/cities")
public class CityController {
    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/country/{countryCode}")
    @Parameter(description = "Country code in ISO 3166-1 alpha-2 format")
    @Operation(summary = "Get cities by country code",
            description = "Get a list of cities by country code in ISO 3166-1 alpha-2 format")
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class))
    )
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<List<City>> getCitiesByCountryCode(
            @PathVariable @Parameter(example = "CL") String countryCode) {
        List<City> cities = service.getCitiesByCountryCode(countryCode);
        return getResponseEntity(cities);
    }

    @GetMapping("/country/{countryCode}/state/{stateCode}")
    @Parameter(description = "Country code in ISO 3166-1 alpha-2 format")
    @Parameter(description = "State code in ISO 3166-2 format")
    @Operation(summary = "Get cities by country code and state code",
            description = "Get a list of cities by country code in ISO 3166-1 alpha-2 format and state code in "
            + "ISO 3166-2 format")
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class))
    )
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<List<City>> getCitiesByCountryCodeAndStateCode(
            @PathVariable @Parameter(example = "CL") String countryCode,
            @PathVariable @Parameter(example = "AN") String stateCode
    ) {
        List<City> cities = service.getCitiesByCountryCodeAndStateCode(countryCode, stateCode);
        return getResponseEntity(cities);
    }

    private ResponseEntity<List<City>> getResponseEntity(List<City> cities) {
        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(cities);
    }
}
