package social.network.ms.users.config;

import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpFilterChain;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.io.IOException;

/**
 * Configuration: Rest Client for Http or Https
 */

@Configuration
public class ConfigRestApi implements HttpFilterChain {

    @Value("${spring.notifications.uri}")
    private String uriNotifications;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

    @Override
    public void proceed(ClassicHttpRequest classicHttpRequest, ResponseTrigger responseTrigger, HttpContext httpContext) throws HttpException, IOException {

    }
}