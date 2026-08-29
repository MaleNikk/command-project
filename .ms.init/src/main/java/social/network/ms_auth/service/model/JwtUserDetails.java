package social.network.ms_auth.service.model;

import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.UUID;

public record JwtUserDetails(UUID id, String email, String password, Set<String>roles, Set<JwtUserTask> tasks) {

    @NonNull
    @Override
    public String toString() {
        return String.format("UserDetails: \n{\n\tid: %s,\n\temail: %s,\n\tpassword: %s,\n\troles: %s,\n\ttasks: %s\n}",
                id(), email(), password(), roles(), tasks());
    }
}