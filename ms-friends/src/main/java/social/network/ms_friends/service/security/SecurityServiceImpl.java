package social.network.ms_friends.service.security;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import social.network.ms_friends.logging.ApplicationLogger;
import social.network.ms_friends.service.kafka.KafkaService;

import java.util.*;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final HashSet<String> data;

    private final String topicRead;

    private final KafkaService service;

    private final ApplicationLogger logger;

    public SecurityServiceImpl(
            @Value("${spring.kafka.topic-read}")
            String topicRead,
            @Autowired
            KafkaService service,
            @Autowired
            ApplicationLogger logger) {
        this.service = service;
        this.logger = logger;
        this.data = new HashSet<>();
        this.topicRead = topicRead;
    }

    @Override
    public UUID checkToken(HttpHeaders headers) {
        logger.printLog("Security service: call method check token.", Level.DEBUG);
        String token = Objects.requireNonNull(headers.getFirst(HttpHeaders.AUTHORIZATION)).substring(8);
        if (!data.contains(token)) {
            fillData();
        }
        return data.contains(token) ? getUUID(token) : null;
    }

    private void fillData() {
        logger.printLog("Security service: call method read data from kafka.", Level.DEBUG);
        data.addAll(service.readNotifications(topicRead));
    }

    private UUID getUUID(String token) {
        logger.printLog("Security service: decode data from token.", Level.DEBUG);
        String userData = Arrays.toString(Base64.getDecoder().decode(token.split("//.")[1]));
        return UUID.fromString(userData.split("id:")[1].split(",")[0]);
    }
}