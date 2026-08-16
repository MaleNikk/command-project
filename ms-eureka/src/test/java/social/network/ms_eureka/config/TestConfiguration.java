package social.network.ms_eureka.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

@SpringBootConfiguration
public class TestConfiguration implements ServletWebServerFactory{

    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {
        return new TomcatServletWebServerFactory(SpringBootTest.WebEnvironment.RANDOM_PORT.ordinal()).getWebServer(initializers);
    }
}