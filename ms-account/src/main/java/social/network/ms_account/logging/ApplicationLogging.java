package social.network.ms_account.logging;

public interface ApplicationLogging {

    void printInfo(String message);

    void printDebug(String message);

    void printError(String message);

    void printTrace(String message);
}
