package social.network.ms_notifications.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import social.network.ms_notifications.logging.ApplicationLogging;

import java.util.UUID;

@Component
public class ApplicationSecurityClass implements ApplicationSecurity {

    private final ApplicationLogging logging;

    @Value("application.security.uri")
    private String securityUri;

    @Autowired
    public ApplicationSecurityClass(ApplicationLogging logging) {
        this.logging = logging;
    }

    @Override
    public UUID checkToken(String token) {
        logging.printInfo("Application security class: call method check token.");
        //ResponseEntity<Boolean> entity = restClient.post().uri(securityUri).contentType(MediaType.APPLICATION_JSON).body(token).retrieve().toEntity(Boolean.class);
        //return Boolean.TRUE.equals(entity.getBody()) ? null : UUID.fromString(token);
        return UUID.randomUUID();
    }

    private RestClient getClient(String uri) {
        return RestClient.builder().baseUrl(uri).build();
    }
}