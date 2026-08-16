package social.network.ms_dialogs.exception.management;

import social.network.ms_dialogs.exception.models.ContentNotFoundException;
import social.network.ms_dialogs.exception.models.SendNotificationException;
import social.network.ms_dialogs.exception.models.TokenValidationException;
import social.network.ms_dialogs.exception.models.UnauthorizedException;
import social.network.ms_dialogs.logger.ApplicationLogging;
import social.network.ms_dialogs.logger.ApplicationLoggingImpl;

public class ExceptionBuilder {
    private static final ApplicationLogging logger = new ApplicationLoggingImpl();

    public static ContentNotFoundException initNFException(String message) {
        logger.getLogger().warning(message);
        return new ContentNotFoundException(message);
    }

    public static TokenValidationException initTVException(String message) {
        logger.getLogger().warning(message);
        return new TokenValidationException(message);
    }

    public static UnauthorizedException initUNException(String message) {
        logger.getLogger().warning(message);
        return new UnauthorizedException(message);
    }

    public static SendNotificationException initSNException(String message) {
        logger.getLogger().warning(message);
        return new SendNotificationException(message);
    }
}
