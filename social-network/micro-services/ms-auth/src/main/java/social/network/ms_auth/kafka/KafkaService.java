package social.network.ms_auth.kafka;

import java.util.List;

public interface KafkaService {

    List<String> readNotifications(String topicId);

    String sendNotification(String data);
}