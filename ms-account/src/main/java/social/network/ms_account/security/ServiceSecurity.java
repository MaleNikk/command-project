package social.network.ms_account.security;

import org.springframework.http.HttpHeaders;

import java.util.UUID;

public interface ServiceSecurity {
    boolean checkToken(HttpHeaders headers);

    UUID getId(HttpHeaders headers);
}