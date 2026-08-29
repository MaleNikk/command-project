package social.network.ms.users.exception;

public class DatabaseConnectException extends RuntimeException {
    public DatabaseConnectException(String message) {
        super(message);
    }
}
