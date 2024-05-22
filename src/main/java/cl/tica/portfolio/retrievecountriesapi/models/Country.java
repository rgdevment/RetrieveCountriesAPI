package cl.tica.portfolio.retrievecountriesapi.models;

import cl.tica.portfolio.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "countries")
public class Country {
    @Id
    private String id;

    @NotBlank
    @Size(max = 80)
    @Indexed
    @JsonView(Views.Single.class)
    private String name;

    @NotBlank
    @Size(max = 80)
    @JsonView(Views.Single.class)
    private String capital;

    @NotBlank
    @Size(max = 80)
    @JsonView(Views.Single.class)
    private String region;

    @NotBlank
    @Size(max = 80)
    @JsonView(Views.Single.class)
    private String subregion;

    @JsonView(Views.Complete.class)
    private List<String> cities;

    @DBRef
    @JsonView(Views.Single.class)
    private Flag flag;

    public @NotBlank @Size(max = 80) String getName() {
        return name;
    }

    public void setName(
            @NotBlank @Size(max = 80) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 80) String getCapital() {
        return capital;
    }

    public void setCapital(
            @NotBlank @Size(max = 80) String capital) {
        this.capital = capital;
    }

    public @NotBlank @Size(max = 80) String getRegion() {
        return region;
    }

    public void setRegion(
            @NotBlank @Size(max = 80) String region) {
        this.region = region;
    }

    public @NotBlank @Size(max = 80) String getSubregion() {
        return subregion;
    }

    public void setSubregion(
            @NotBlank @Size(max = 80) String subregion) {
        this.subregion = subregion;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
