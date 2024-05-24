package cl.tica.portfolio.retrievecountriesapi.documents;

import cl.tica.portfolio.retrievecountriesapi.v1.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flags")
public class Flag {
    @Id
    private String id;

    @NotNull
    @Size(max = 40)
    @JsonView(Views.Single.class)
    private String ico;

    @NotBlank
    @Size(max = 256)
    @JsonView(Views.Single.class)
    private String png;

    @NotBlank
    @Size(max = 256)
    @JsonView(Views.Single.class)
    private String svg;

    @Size(max = 2048)
    @JsonProperty("alt")
    @JsonView(Views.Single.class)
    private String description;

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
}
