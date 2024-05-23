package cl.tica.portfolio.retrievecountriesapi.application.controllers.advices;

import cl.tica.portfolio.retrievecountriesapi.application.models.ExceptionWrappingError;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class GlobalExceptionHandlerTest {
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private ServletWebRequest webRequest;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        when(webRequest.getRequest()).thenReturn(request);
        when(request.getRequestURI()).thenReturn("/test");
    }

    @Test
    void shouldHandleCommonError() {
        Exception exception = new Exception("Test Exception");
        when(webRequest.getContextPath()).thenReturn("/test");

        ResponseEntity<ExceptionWrappingError>
                responseEntity = globalExceptionHandler.handleCommonError(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Exception", Objects.requireNonNull(responseEntity.getBody()).getTitle());
        assertEquals("Test Exception", responseEntity.getBody().getDetails());
    }

    @Test
    void shouldHandleDatabaseError() {
        Exception exception = new Exception("Test Exception");
        when(webRequest.getContextPath()).thenReturn("/test");

        ResponseEntity<ExceptionWrappingError> responseEntity =
                globalExceptionHandler.handleDatabaseError(exception, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal Server Error", Objects.requireNonNull(responseEntity.getBody()).getTitle());
        assertEquals("Test Exception", responseEntity.getBody().getDetails());
    }

    @Test
    void shouldHandleErrorResponse() {
        Exception exception = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test Exception");
        when(webRequest.getContextPath()).thenReturn("/test");

        ResponseEntity<ExceptionWrappingError> responseEntity =
                globalExceptionHandler.handleCommonError(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Bad Request", Objects.requireNonNull(responseEntity.getBody()).getTitle());
        assertEquals("Test Exception", responseEntity.getBody().getDetails());
        assertEquals("/test", responseEntity.getBody().getInstance());
        assertNotNull(responseEntity.getBody().getTimespan());
    }
}
