package social.network.ms_friends.service.kafka;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import social.network.ms_friends.logging.ApplicationLogger;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class KafkaServiceImpl implements KafkaService {

    private final ApplicationLogger logger;

    private final KafkaTemplate<String, String> template;

    private final String topicSend;

    public KafkaServiceImpl(
            @Autowired
            ApplicationLogger logger,
            @Autowired
            KafkaTemplate<String, String> template,
            @Value("${spring.kafka.topic-send}")
            String topicSend) {
        this.logger = logger;
        this.template = template;
        this.topicSend = topicSend;
    }

    @Override
    public List<String> readNotifications(String topicId) {
        logger.printLog("Kafka service: call method read notifications.", Level.INFO);
        var result = template.receive(topicId, 0, 0L);
        assert result != null;
        return List.of(result.value());
    }

    @Override
    public String getId(String token) {
        logger.printLog("Kafka service: call method check token.", Level.INFO);
        return "";
    }

    @Override
    public String sendNotification(String data) {
        logger.printLog("Kafka service: call send notification.", Level.INFO);
        CompletableFuture<?> future = template.send(topicSend, data);
        return future.isDone() ? "Sending data to kafka complete successfully! " : "Send data to kafka don't complete!";
    }
}