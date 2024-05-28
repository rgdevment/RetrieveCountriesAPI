package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.serializer.CityListSerializer;
import cl.restapi.retrievecountriesapi.serializer.StateListSerializer;
import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "countries")
public class Country {
    @Id
    private String id;

    @NotBlank
    @Indexed
    @JsonView(Views.Single.class)
    private String name;

    @NotBlank
    @JsonView(Views.Single.class)
    private String capital;

    @NotBlank
    @JsonView(Views.Single.class)
    private String region;

    @NotBlank
    @JsonView(Views.Single.class)
    private String subregion;

    @NotBlank
    @JsonView(Views.Single.class)
    private String iso2;

    @NotBlank
    @JsonView(Views.Single.class)
    private String iso3;

    @NotBlank
    @JsonView(Views.Single.class)
    private String tld;

    @NotBlank
    @Field("phone_code")
    @JsonView(Views.Single.class)
    private String phoneCode;

    @NotNull
    @JsonView(Views.Single.class)
    private Double latitude;

    @NotNull
    @JsonView(Views.Single.class)
    private Double longitude;

    @JsonView(Views.Single.class)
    private Map<String, String> currency;

    @JsonView(Views.Single.class)
    private Map<String, String> flag;

    @DBRef
    @JsonSerialize(using = StateListSerializer.class)
    @JsonView({Views.Complete.class, Views.WithStates.class})
    private List<State> states;

    @DBRef
    @JsonSerialize(using = CityListSerializer.class)
    @JsonView({Views.Complete.class, Views.WithCities.class})
    private List<City> cities;

    public Country() {
        this.states = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getCapital() {
        return capital;
    }

    public void setCapital(@NotBlank String capital) {
        this.capital = capital;
    }

    public @NotBlank String getRegion() {
        return region;
    }

    public void setRegion(@NotBlank String region) {
        this.region = region;
    }

    public @NotBlank String getSubregion() {
        return subregion;
    }

    public void setSubregion(@NotBlank String subregion) {
        this.subregion = subregion;
    }

    public @NotBlank String getIso2() {
        return iso2;
    }

    public void setIso2(@NotBlank String iso2) {
        this.iso2 = iso2;
    }

    public @NotBlank String getIso3() {
        return iso3;
    }

    public void setIso3(@NotBlank String iso3) {
        this.iso3 = iso3;
    }

    public @NotBlank String getTld() {
        return tld;
    }

    public void setTld(@NotBlank String tld) {
        this.tld = tld;
    }

    public @NotBlank String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(@NotBlank String phoneCode) {
        this.phoneCode = phoneCode;
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

    public Map<String, String> getCurrency() {
        return currency;
    }

    public void setCurrency(Map<String, String> currency) {
        this.currency = currency;
    }

    public Map<String, String> getFlag() {
        return flag;
    }

    public void setFlag(Map<String, String> flag) {
        this.flag = flag;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
