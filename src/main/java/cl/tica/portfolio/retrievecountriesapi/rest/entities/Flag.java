package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import cl.tica.portfolio.retrievecountriesapi.rest.enums.FlagFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "flags")
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flags_seq")
    @SequenceGenerator(name = "flags_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlagFormat format;

    @NotBlank
    @Size(max = 256)
    @Column(nullable = false)
    private String path;

    @Size(max = 2048)
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public @NotNull FlagFormat getFormat() {
        return format;
    }

    public void setFormat(
            @NotNull FlagFormat format) {
        this.format = format;
    }

    public @NotBlank @Size(max = 256) String getPath() {
        return path;
    }

    public void setPath(
            @NotBlank @Size(max = 256) String path) {
        this.path = path;
    }

    public @Size(max = 2048) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 2048) String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
