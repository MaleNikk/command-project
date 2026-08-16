package social.network.ms_notifications.listening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import social.network.ms_notifications.logging.ApplicationLogging;

@Component
public class NotificationListener {

    private final ApplicationLogging logging;

    @Autowired
    public NotificationListener(ApplicationLogging logging) {
        this.logging = logging;
    }

    @KafkaListener(
            topics = "${application.kafka.topic}",
            groupId = "${application.kafka.groupId}"
    )
    public void listenEvent(@Payload String notification,
                            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp){
        logging.printInfo("Received data: " + notification);
        logging.printInfo(String.format("Key: %s; \t\nPartition: %s; \t\nTopic: %s; \n\tTimestamp: %s", key,partition,topic,timestamp));
    }
}