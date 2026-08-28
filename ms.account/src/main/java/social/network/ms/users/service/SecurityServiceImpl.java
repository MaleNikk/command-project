package social.network.ms.users.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import social.network.ms.users.contract.ServiceSecurity;

import java.util.UUID;

@Service
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