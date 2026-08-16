package social.network.ms_dialogs.service.kafka;

import social.network.ms_dialogs.dto.MessageDto;

@FunctionalInterface
public interface KafkaService {
    boolean pushNotification(MessageDto message);
}
