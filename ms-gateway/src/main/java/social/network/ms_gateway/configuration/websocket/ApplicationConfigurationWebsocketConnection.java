package social.network.ms_gateway.configuration.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import social.network.ms_gateway.configuration.websocket.dto.GatewayWebsocketHandling;

@Configuration
@EnableWebSocket
public class ApplicationConfigurationWebsocketConnection implements WebSocketConfigurer {

    @Autowired
    private GatewayWebsocketHandling websocketHandling;

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        System.out.println("Initialize setting WebSocketHandler.");
        String endpoint = "/api/v1/streaming/ws";
        registry.addHandler(websocketHandling, endpoint).setAllowedOriginPatterns("*");
    }
}