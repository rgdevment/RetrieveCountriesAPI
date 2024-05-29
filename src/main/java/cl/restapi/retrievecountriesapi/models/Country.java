package cl.restapi.retrievecountriesapi.models;

import cl.restapi.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "countries")
public class Country {
    @Id
    private String id;

    @Indexed
    @JsonView(Views.Single.class)
    private String name;

    @Indexed
    @JsonView(Views.Single.class)
    private String capital;

    @Indexed
    @JsonView(Views.Single.class)
    private String iso2;

    @Indexed
    @JsonView(Views.Single.class)
    private String iso3;

    @JsonView(Views.Single.class)
    private String region;

    @JsonView(Views.Single.class)
    private String subregion;

    @JsonView(Views.Single.class)
    private String tld;

    @Field("phone_code")
    @JsonView(Views.Single.class)
    private String phoneCode;

    @JsonView(Views.Single.class)
    private Double latitude;

    @JsonView(Views.Single.class)
    private Double longitude;

    @JsonView(Views.Single.class)
    private Currency currency;

    @JsonView(Views.Single.class)
    private Flag flags;

    @JsonView({Views.Complete.class, Views.WithStates.class})
    private List<State> states;

    @JsonView({Views.Complete.class, Views.WithCities.class})
    private List<City> cities;

    public Country() {
        this.states = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Flag getFlags() {
        return flags;
    }

    public void setFlags(Flag flags) {
        this.flags = flags;
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
