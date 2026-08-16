package social.network.ms_dialogs.logger;

import java.util.logging.Logger;

public interface ApplicationLogging {
    Logger getLogger();

    void printLog(String message);
}
