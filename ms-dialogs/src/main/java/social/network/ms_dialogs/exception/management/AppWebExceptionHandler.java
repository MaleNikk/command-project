package social.network.ms_dialogs.exception.management;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import social.network.ms_dialogs.exception.models.ContentNotFoundException;
import social.network.ms_dialogs.exception.models.TokenValidationException;
import social.network.ms_dialogs.exception.models.UnauthorizedException;

@RestControllerAdvice
public class AppWebExceptionHandler {
    @ExceptionHandler({TokenValidationException.class})
    public ResponseEntity<AppErrorResponseBody> validTokenExceptionHandler(TokenValidationException exception, WebRequest webRequest) {
        return this.buildResponse(HttpStatus.FORBIDDEN, exception, webRequest);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<AppErrorResponseBody> unauthorisedExceptionHandler(UnauthorizedException exception, WebRequest webRequest) {
        return this.buildResponse(HttpStatus.BAD_REQUEST, exception, webRequest);
    }

    @ExceptionHandler({ContentNotFoundException.class})
    public ResponseEntity<AppErrorResponseBody> notFoundExceptionHandler(ContentNotFoundException exception, WebRequest webRequest) {
        return this.buildResponse(HttpStatus.NOT_FOUND, exception, webRequest);
    }

    private ResponseEntity<AppErrorResponseBody> buildResponse(HttpStatus httpStatus, Exception exception, WebRequest webRequest) {
        return ResponseEntity.status(httpStatus).body(AppErrorResponseBody.getInstance(exception.getMessage(), webRequest.getDescription(false)));
    }
}