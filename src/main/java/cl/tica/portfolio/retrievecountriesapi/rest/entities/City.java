package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cities", indexes = {
        @Index(name = "idx_city_name", columnList = "name")
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_seq")
    @SequenceGenerator(name = "cities_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String name;

    @NotNull
    private Integer population;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public @NotBlank @Size(max = 80) String getName() {
        return name;
    }

    public void setName(
            @NotBlank @Size(max = 80) String name) {
        this.name = name;
    }

    public @NotNull Integer getPopulation() {
        return population;
    }

    public void setPopulation(@NotNull Integer population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
