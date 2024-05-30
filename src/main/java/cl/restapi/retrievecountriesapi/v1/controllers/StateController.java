package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.services.StateService;
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

@Tag(name = "States")
@RestController
@RequestMapping("/v1/states")
public class StateController {
    private final StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<List<State>> getCitiesByCountryCode(
            @PathVariable @Parameter(example = "CL") String countryCode) {
        List<State> cities = service.getStatesByCountryCode(countryCode);

        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(cities);
    }
}
