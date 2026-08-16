package social.network.ms_account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Value("${spring.notifications.uri}")
    private String uriNotifications;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}