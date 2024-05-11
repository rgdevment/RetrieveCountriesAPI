package cl.tica.portfolio.retrievecountriesapi.application.exceptions;

import org.springframework.http.HttpStatusCode;

public interface ApiException {
    HttpStatusCode getStatusCode();

    String getReason();

    String getInternalCode();
}
