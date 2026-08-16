package social.network.ms_notifications.exeption.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import social.network.ms_notifications.logging.ApplicationLogging;

@Component
public class ExceptionBuilder {

    private final ApplicationLogging logger;

    @Autowired
    public ExceptionBuilder(ApplicationLogging logger) {
        this.logger = logger;
    }

    public ContentNotFoundException initNFException(String message) {
        logger.printError(message);
        return new ContentNotFoundException(message);
    }

    public TokenValidationException initTVException(String message) {
        logger.printError(message);
        return new TokenValidationException(message);
    }

    public UnauthorizedException initUNException(String message) {
        logger.printError(message);
        return new UnauthorizedException(message);
    }

    public SendNotificationException initSNException(String message) {
        logger.printError(message);
        return new SendNotificationException(message);
    }
}