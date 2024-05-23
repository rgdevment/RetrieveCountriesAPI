package cl.tica.portfolio.retrievecountriesapi.populate.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FetchDataException extends ResponseStatusException {
    public FetchDataException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}

