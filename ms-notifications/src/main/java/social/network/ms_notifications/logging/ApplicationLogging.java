package social.network.ms_notifications.logging;

public interface ApplicationLogging {

    void printInfo(String message);

    void printDebug(String message);

    void printError(String message);

    void printTrace(String message);
}