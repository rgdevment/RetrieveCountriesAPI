package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import cl.tica.portfolio.retrievecountriesapi.rest.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @Size(max = 40)
    @JsonView(Views.Single.class)
    private String ico;

    @NotBlank
    @Size(max = 256)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String png;

    @NotBlank
    @Size(max = 256)
    @Column(nullable = false)
    @JsonView(Views.Single.class)
    private String svg;

    @Size(max = 2048)
    @JsonProperty("alt")
    @JsonView(Views.Single.class)
    private String description;

    @OneToOne(mappedBy = "flag")
    @JsonIgnore
    private Country country;

    public @NotNull @Size(max = 40) String getIco() {
        return ico;
    }

    public void setIco(
            @NotNull @Size(max = 40) String ico) {
        this.ico = ico;
    }

    public @NotBlank @Size(max = 256) String getPng() {
        return png;
    }

    public void setPng(
            @NotBlank @Size(max = 256) String png) {
        this.png = png;
    }

    public @NotBlank @Size(max = 256) String getSvg() {
        return svg;
    }

    public void setSvg(
            @NotBlank @Size(max = 256) String svg) {
        this.svg = svg;
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
