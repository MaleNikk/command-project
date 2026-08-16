package social.network.ms_notifications.security;

import java.util.UUID;

@FunctionalInterface
public interface ApplicationSecurity {

    UUID checkToken(String token);
}