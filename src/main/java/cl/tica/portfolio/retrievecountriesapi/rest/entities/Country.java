package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String capital;

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

    @NotBlank
    @Size(max = 40)
    @Column(nullable = false)
    private String flag;

    @OneToMany(mappedBy = "country")
    private Set<Flag> flags;

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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public @NotBlank @Size(max = 40) String getFlag() {
        return flag;
    }

    public void setFlag(
            @NotBlank @Size(max = 40) String flag) {
        this.flag = flag;
    }

    public Set<Flag> getFlags() {
        return flags;
    }

    public void setFlags(Set<Flag> flags) {
        this.flags = flags;
    }
}
