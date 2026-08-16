package social.network.ms_dialogs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import social.network.ms_dialogs.logger.ApplicationLogging;

@Configuration
public class ApplicationServerPortConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Autowired
    private ApplicationLogging logger;
    @Value("${server.port}")
    private Integer serverPort;

    public void customize(ConfigurableWebServerFactory factory) {
        logger.getLogger().info(String.format("Application server start at port: %s", serverPort));
        factory.setPort(serverPort);
    }
}
