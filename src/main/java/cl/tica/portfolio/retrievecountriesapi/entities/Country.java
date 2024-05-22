package cl.tica.portfolio.retrievecountriesapi.entities;

import cl.tica.portfolio.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "countries", indexes = {
        @Index(name = "idx_name", columnList = "name")
})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countries_seq")
    @SequenceGenerator(name = "countries_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String name;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String capital;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String region;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String subregion;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    @JsonView(Views.Complete.class)
    private List<City> cities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flag_id")
    @JsonView(Views.Single.class)
    private Flag flag;

    @JsonIgnore
    public List<City> getCities() {
        return cities;
    }

    @JsonProperty("cities")
    public List<String> getCityNames() {
        return cities.stream()
                .map(City::getName)
                .toList();
    }

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

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
