package social.network.ms_account.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoggingImpl implements ApplicationLogging {

    private final Logger logger ;

    public ApplicationLoggingImpl() {
        this.logger = LoggerFactory.getLogger("Service accounts logging.");
        logger.info("Initialize application logging.");
    }

    @Override
    public void printInfo(String message) {
        logger.info(message);
    }

    @Override
    public void printDebug(String message) {
        logger.info(message);
    }

    @Override
    public void printError(String message) {
        logger.info(message);
    }

    @Override
    public void printTrace(String message) {
        logger.info(message);
    }
}
