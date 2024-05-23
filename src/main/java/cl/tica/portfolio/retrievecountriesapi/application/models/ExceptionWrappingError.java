package cl.tica.portfolio.retrievecountriesapi.application.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;

public class ExceptionWrappingError {
    private final LocalDateTime timespan;
    private final String title;
    private final String details;
    private final String instance;

    public ExceptionWrappingError(String title, String details, String instance) {
        this.timespan = LocalDateTime.now();
        this.title = title;
        this.details = details;
        this.instance = instance;
    }

    public LocalDateTime getTimespan() {
        return timespan;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getInstance() {
        return instance;
    }

    public String toJSONString() {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode node = mapper.createObjectNode();
        node.put("timespan", getTimespan().toString());
        node.put("title", getTitle());
        node.put("details", getDetails());
        node.put("instance", getInstance());

        return node.toString();
    }
}
