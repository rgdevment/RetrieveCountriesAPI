package cl.restapi.retrievecountriesapi.dto;

import cl.restapi.retrievecountriesapi.models.City;

public record CityResponse(String name, Double latitude, Double longitude) {
    public static CityResponse fromCity(City city) {
        return new CityResponse(city.name(), city.latitude(), city.longitude());
    }
}
