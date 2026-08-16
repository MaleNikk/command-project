package social.network.ms_friends.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoggingImpl implements ApplicationLogger {

    private final Logger logger;

    public ApplicationLoggingImpl() {
        this.logger = LoggerFactory.getLogger("Application logger");
        logger.info("Initialize application logging.");
    }

    @Override
    public void printLog(String message, Level level) {
        switch (level) {
            case DEBUG -> logger.debug(message);
            case ERROR -> logger.error(message);
            case TRACE -> logger.trace(message);
            case WARN -> logger.warn(message);
            default -> logger.info(message);
        }
    }
}