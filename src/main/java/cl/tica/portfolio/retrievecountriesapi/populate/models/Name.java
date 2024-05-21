package cl.tica.portfolio.retrievecountriesapi.populate.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    @JsonAlias({"common", "name"})
    private String common;

    @JsonCreator
    public Name(JsonNode jsonNode) {
        if (jsonNode.isTextual()) {
            this.common = jsonNode.asText();
        } else {
            this.common = jsonNode.get("common").asText();
        }
    }

    public String getCommon() {
        return common;
    }
}
