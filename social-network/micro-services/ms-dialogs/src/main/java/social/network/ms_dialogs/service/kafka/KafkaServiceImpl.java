package social.network.ms_dialogs.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import social.network.ms_dialogs.dto.MessageDto;
import social.network.ms_dialogs.dto.Notification;
import social.network.ms_dialogs.logger.ApplicationLogging;

import java.time.Instant;
import java.util.Date;

@Service
public class KafkaServiceImpl implements KafkaService {
    @Value("${application.kafka.topic}")
    private String topic;
    @Autowired
    private ApplicationLogging logger;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaServiceImpl(@Autowired KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean pushNotification(MessageDto message) {
        logger.printLog(String.format("Notification service. Send notification: %s", message));
        kafkaTemplate.send(topic, (
                new Notification(
                        message.id(),
                        "DIALOG",
                        String.valueOf(Date.from(Instant.now())),
                        message.messageText())
        ).toString());
        return Boolean.TRUE;
    }
}
