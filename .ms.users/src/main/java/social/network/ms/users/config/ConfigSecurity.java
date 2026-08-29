package social.network.ms.users.config;

import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpFilterChain;
import org.apache.hc.core5.http.io.HttpFilterHandler;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ConfigSecurity implements HttpFilterHandler {

    @Override
    public void handle(
            ClassicHttpRequest classicHttpRequest,
            HttpFilterChain.ResponseTrigger responseTrigger,
            HttpContext httpContext,
            HttpFilterChain httpFilterChain) throws HttpException, IOException {

    }
}