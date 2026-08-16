package social.network.ms_dialogs.logger;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ApplicationLoggingImpl implements ApplicationLogging {
    private final Logger logger = Logger.getLogger("Dialog service logger.");

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public void printLog(String message) {
        this.getLogger().info(message);
    }
}
