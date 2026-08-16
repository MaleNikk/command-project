package social.network.ms_friends.service.security;

import org.springframework.http.HttpHeaders;

import java.util.UUID;

@FunctionalInterface
public interface SecurityService {
    UUID checkToken(HttpHeaders headers);
}