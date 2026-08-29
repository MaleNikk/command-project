package social.network.ms_auth.exception;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException implements Supplier<RuntimeException> {
    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public RuntimeException get() {
        return new RuntimeException(getMessage());
    }
}
