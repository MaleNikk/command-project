package social.network.ms.users.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.servlet.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TestServiceConfiguration {

    @Bean
    public ServletWebServerFactory webServerFactory() {
        return new TomcatServletWebServerFactory(SpringBootTest.WebEnvironment.RANDOM_PORT.ordinal());
    }
}