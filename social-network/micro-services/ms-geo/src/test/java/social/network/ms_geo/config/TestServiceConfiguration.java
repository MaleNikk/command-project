package social.network.ms_geo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TestServiceConfiguration {

    @Bean
    public ServletWebServerFactory webServerFactory() {
        return new TomcatServletWebServerFactory(SpringBootTest.WebEnvironment.RANDOM_PORT.ordinal());
    }
}