package social.network.ms.users.contract;

import org.springframework.http.HttpHeaders;

import java.util.UUID;

public interface ServiceSecurity {
    boolean checkToken(HttpHeaders headers);

    UUID getId(HttpHeaders headers);
}