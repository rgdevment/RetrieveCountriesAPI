package cl.tica.portfolio.retrievecountriesapi.rest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "countries", indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_capital", columnList = "capital")
})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countries_seq")
    @SequenceGenerator(name = "countries_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String capital;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String region;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String subregion;

    @ElementCollection
    private List<String> cities;

    @OneToMany(mappedBy = "country")
    private Set<Flag> flags;
}
