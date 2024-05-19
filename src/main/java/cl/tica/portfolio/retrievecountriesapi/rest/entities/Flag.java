package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "flags")
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flags_seq")
    @SequenceGenerator(name = "flags_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String format;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public @NotBlank @Size(max = 10) String getFormat() {
        return format;
    }

    public void setFormat(
            @NotBlank @Size(max = 10) String format) {
        this.format = format;
    }

    public @NotBlank @Size(max = 120) String getPath() {
        return path;
    }

    public void setPath(
            @NotBlank @Size(max = 120) String path) {
        this.path = path;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
