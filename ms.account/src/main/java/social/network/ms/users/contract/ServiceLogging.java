package social.network.ms.users.contract;

public interface ServiceLogging {

    void printInfo(String message);

    void printDebug(String message);

    void printError(String message);

    void printTrace(String message);
}
