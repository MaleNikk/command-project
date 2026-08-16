package social.network.ms_auth.dto.request;

import org.springframework.lang.NonNull;
import social.network.ms_auth.repository.entity.RoleType;

import java.util.Set;

public record UserRequest (String email, String password, Set<RoleType> roles) {

    @NonNull
    @Override
    public String toString() {
        return String.format("Save user info:\n\temail: %s\n\tpassword: %s,\n\troles: %s.",
                email(), password(), roles());
    }
}