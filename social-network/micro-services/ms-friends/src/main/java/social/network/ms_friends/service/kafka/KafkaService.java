package social.network.ms_friends.service.kafka;

import java.util.List;

public interface KafkaService {

    List<String> readNotifications(String topicId);

    String getId(String token);

    String sendNotification(String data);
}