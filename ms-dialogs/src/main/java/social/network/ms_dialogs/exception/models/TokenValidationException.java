package social.network.ms_dialogs.exception.models;

import java.text.MessageFormat;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String token, String message) {
        super(MessageFormat.format("Error trying to refresh by token: {0} : {1}", token, message));
    }

    public TokenValidationException(String message) {
        super(message);
    }
}
