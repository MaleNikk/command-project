package social.network.ms_notifications.storage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.logging.ApplicationLogging;

import java.util.concurrent.CompletableFuture;

@Repository
public class NotificationStorageImpl implements NotificationStorage {

    private final String topic;

    private final ApplicationLogging logging;

    private final KafkaTemplate<?,String> template;

    public NotificationStorageImpl(
            @Value("${application.kafka.topic}")
            String topic,
            @Autowired
            ApplicationLogging logging,
            @Autowired
            KafkaTemplate<?,String> template) {
        this.topic = topic;
        this.logging = logging;
        this.template = template;
    }

    @Override
    public boolean sendNotification(NotificationEntity entity) {
        logging.printInfo("Notifications storage: push content to kafka.");
        System.out.println("Current topic for send: " + topic);
        CompletableFuture<?> future = template.send(topic, entity.toString());
        System.out.println("Data from consumer: " + getAllNotifications().toString());
        return future.isDone();
    }

    public ConsumerRecord<?,?> getAllNotifications() {
        return template.receive(topic,7,0L);
    }
}