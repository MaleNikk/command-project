package social.network.ms_account.security;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityServiceImpl implements ServiceSecurity {
    @Override
    public boolean checkToken(HttpHeaders headers) {
        return Boolean.TRUE;
    }

    @Override
    public UUID getId(HttpHeaders headers) {
        return null;
    }
}
