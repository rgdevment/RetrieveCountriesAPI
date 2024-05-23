package cl.tica.portfolio.retrievecountriesapi.application.controllers.advices;

import cl.tica.portfolio.retrievecountriesapi.application.models.ExceptionWrappingError;
import jakarta.servlet.ServletException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServletException.class, ResponseStatusException.class, ConversionNotSupportedException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class, BindException.class, AsyncRequestTimeoutException.class})
    public ResponseEntity<ExceptionWrappingError> handleCommonError(Exception ex, WebRequest request) {
        ResponseEntity<ExceptionWrappingError> result;
        String fullPath = getFullPath(request);

        if (ex instanceof ErrorResponse errorResponse) {
            ExceptionWrappingError error = new ExceptionWrappingError(
                    errorResponse.getBody().getTitle(),
                    errorResponse.getBody().getDetail(),
                    fullPath
            );

            result = ResponseEntity.status(errorResponse.getStatusCode()).body(error);
        } else {
            ExceptionWrappingError error = new ExceptionWrappingError(
                    ex.getClass().getSimpleName(),
                    ex.getMessage(),
                    fullPath
            );
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
        }


        return result;
    }

    @ExceptionHandler({DataAccessException.class, TransactionException.class, Exception.class})
    public ResponseEntity<ExceptionWrappingError> handleDatabaseError(Exception ex, WebRequest request) {
        String fullPath = getFullPath(request);

        ExceptionWrappingError error = new ExceptionWrappingError(
                "Internal Server Error",
                ex.getMessage(),
                fullPath
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    private static String getFullPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
