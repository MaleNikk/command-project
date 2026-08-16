package social.network.ms_auth.dto.responce;

import org.springframework.lang.NonNull;

public record SimpleResponseJwt (String message) {

    @NonNull
    @Override
    public String toString() {
        return String.format("\n%s", message());
    }
}