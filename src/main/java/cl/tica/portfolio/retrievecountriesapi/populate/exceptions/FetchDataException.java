package cl.tica.portfolio.retrievecountriesapi.populate.exceptions;

import cl.tica.portfolio.retrievecountriesapi.application.exceptions.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FetchDataException extends ResponseStatusException implements ApiException {

    public static final String INVALID_CONFIRMATION = "FETCH_FAILED";

    public FetchDataException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public String getInternalCode() {
        return INVALID_CONFIRMATION;
    }
}

