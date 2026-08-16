package social.network.ms_dialogs.service.security;

import org.springframework.http.HttpHeaders;

@FunctionalInterface
public interface SecurityService {
    String getUserId(HttpHeaders request);
}
