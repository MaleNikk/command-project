package social.network.ms_gateway.configuration.websocket.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class GatewayWebsocketHandling extends AbstractWebSocketHandler {

    private final Logger logger;

    private final ObjectMapper objectMapper;

    //@Value("${sending.message.dialogs.path}")
    private final String path;

    public GatewayWebsocketHandling() {
        this.logger = Logger.getLogger("Websocket service class.");
        this.objectMapper = new ObjectMapper();
        this.path = "http://ms-dialogs:8077/api/v1/dialogs/sending/message";
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws IOException {
        logger.info(String.format("Open new websocket session with id: %s", session.getId()));
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        logger.info(String.format("Session closed: id - %s , status code: %s", session.getId(), status.getCode()));
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        String text = message.getPayload().replaceAll("\\s+", " ");
        logger.info(String.format("Websocket session: send message to dialog : %s!", text));
        MessageRequestWebsocket request = objectMapper.readValue(text, MessageRequestWebsocket.class);
        if (request != null) {
            MessageResponseWebsocket responseWebsocket = getClient(path).put().body(request).retrieve().body(MessageResponseWebsocket.class);
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(responseWebsocket)));
        } else {
            session.sendMessage(message);
        }
    }

    private RestClient getClient(String uri) {
        return RestClient.builder().baseUrl(uri).build();
    }
}