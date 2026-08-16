package social.network.ms_notifications.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoggingClass implements ApplicationLogging {

    private final Logger logger;

    public ApplicationLoggingClass() {
        this.logger = LoggerFactory.getLogger("Notifications service logging.");
        logger.info("Initialize notifications logging class.");
    }

    @Override
    public void printInfo(String message) {
        logger.info(message);
    }

    @Override
    public void printDebug(String message) {
        logger.debug(message);
    }

    @Override
    public void printError(String message) {
        logger.error(message);
    }

    @Override
    public void printTrace(String message) {
        logger.trace(message);
    }
}
