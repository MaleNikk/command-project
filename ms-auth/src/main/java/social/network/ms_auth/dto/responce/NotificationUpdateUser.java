package social.network.ms_auth.dto.responce;

import org.springframework.lang.NonNull;

import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;

public record NotificationUpdateUser(UUID id, String firstName, String lastName, String password, Date updated, InetAddress address) {

    @NonNull
    @Override
    public String toString() {
        return String.format("Register user:\n\tid: %s,\n\tfirstName: %s,\n\tlastName: %s,\n\tupdated: %s,\n\taddress: %s",
                id(), firstName(), lastName(), updated(), address());
    }
}