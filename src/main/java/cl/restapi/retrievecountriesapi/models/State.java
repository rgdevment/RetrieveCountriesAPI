package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "states")
public class State {
    @Id
    private String id;

    @NotBlank
    @Indexed
    @JsonView(Views.Single.class)
    private String name;

    @NotBlank
    @Indexed
    @JsonView(Views.Single.class)
    private String code;

    @NotNull
    @JsonView(Views.Single.class)
    private Double latitude;

    @NotNull
    @JsonView(Views.Single.class)
    private Double longitude;

    @DBRef
    @JsonIgnore
    private List<City> cities;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getCode() {
        return code;
    }

    public void setCode(@NotBlank String code) {
        this.code = code;
    }

    public @NotNull Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NotNull Double latitude) {
        this.latitude = latitude;
    }

    public @NotNull Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NotNull Double longitude) {
        this.longitude = longitude;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
