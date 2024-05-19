package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "capitals", joinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "capital")
    private List<String> capitals = new ArrayList<>();

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String region;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String subregion;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;

    @OneToMany(mappedBy = "country")
    private Set<Flag> flags;

    public @NotBlank @Size(max = 80) String getName() {
        return name;
    }

    public void setName(
            @NotBlank @Size(max = 80) String name) {
        this.name = name;
    }

    public List<String> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<Flag> getFlags() {
        return flags;
    }

    public void setFlags(Set<Flag> flags) {
        this.flags = flags;
    }
}
