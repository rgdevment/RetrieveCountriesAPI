package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.City;
import cl.restapi.retrievecountriesapi.services.CityService;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<List<City>> getCitiesByCountryCode(
            @PathVariable @Parameter(example = "CL") String countryCode) {
        List<City> cities = service.getCitiesByCountryCode(countryCode);

        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(cities);
    }

    @GetMapping("/country/{countryCode}/state/{stateCode}")
    public ResponseEntity<List<City>> getCitiesByCountryCodeAndStateCode(
            @PathVariable @Parameter(example = "CL") String countryCode,
            @PathVariable @Parameter(example = "AN") String stateCode
    ) {
        List<City> cities = service.getCitiesByCountryCodeAndStateCode(countryCode, stateCode);

        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(cities);
    }
}
