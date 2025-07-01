package cl.restapi.retrievecountriesapi.v1.controllers;

import cl.restapi.retrievecountriesapi.dto.StateResponse;
import cl.restapi.retrievecountriesapi.models.State;
import cl.restapi.retrievecountriesapi.services.StateService;
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

@Tag(name = "States")
@RestController
@RequestMapping("/v1/states")
public class StateController {
    private final StateService service;

    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping("/country/{countryCode}")
    @Operation(summary = "Get states by country code.",
            description = "Get a list of states by country code in ISO 3166-1 alpha-2 format.")
    @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = State.class))
    )
    @ApiResponse(responseCode = "204", description = "No content", content = @Content)
    public ResponseEntity<List<StateResponse>> getCitiesByCountryCode(
            @PathVariable @Parameter(example = "CL") String countryCode) {
        List<StateResponse> states = service.getStatesByCountryCode(countryCode);

        if (states.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(states);
    }
}
