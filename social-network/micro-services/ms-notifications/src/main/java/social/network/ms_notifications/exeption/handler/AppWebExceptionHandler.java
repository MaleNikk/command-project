package social.network.ms_notifications.exeption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import social.network.ms_notifications.exeption.exception.ContentNotFoundException;
import social.network.ms_notifications.exeption.exception.TokenValidationException;
import social.network.ms_notifications.exeption.exception.UnauthorizedException;

@RestControllerAdvice
public class AppWebExceptionHandler {

    @ExceptionHandler(value = TokenValidationException.class)
    public ResponseEntity<AppErrorResponseBody> validTokenExceptionHandler(
            TokenValidationException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.FORBIDDEN, exception, webRequest);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<AppErrorResponseBody> unauthorisedExceptionHandler(
            UnauthorizedException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception, webRequest);
    }

    @ExceptionHandler(value = ContentNotFoundException.class)
    public ResponseEntity<AppErrorResponseBody> notFoundExceptionHandler(
            ContentNotFoundException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.NOT_FOUND, exception, webRequest);
    }

    private ResponseEntity<AppErrorResponseBody> buildResponse(
            HttpStatus httpStatus, Exception exception, WebRequest webRequest) {
        return ResponseEntity.status(httpStatus)
                .body(AppErrorResponseBody
                        .getInstance(exception.getMessage(), webRequest.getDescription(false)));

    }
}
