package social.network.ms_auth.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import social.network.ms_auth.exception.AlreadyExistsException;
import social.network.ms_auth.exception.EntityNotFoundException;
import social.network.ms_auth.exception.RefreshTokenException;

@RestControllerAdvice
public class WebAppExceptionHandler {

    @ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<ErrorResponseBody> refreshTokenExceptionHandler(
            RefreshTokenException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.FORBIDDEN, exception, webRequest);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseBody> alreadyExistsHandler(
            AlreadyExistsException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception, webRequest);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> notFoundHandler(
            EntityNotFoundException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.NOT_FOUND, exception, webRequest);
    }

    private ResponseEntity<ErrorResponseBody> buildResponse(
            HttpStatus httpStatus,
            Exception exception,
            WebRequest webRequest
    ) {
        return ResponseEntity.status(httpStatus)
                .body(new ErrorResponseBody(
                        exception.getMessage(),
                        webRequest.getDescription(false)));
    }
}