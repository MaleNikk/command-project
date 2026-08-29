package social.network.ms.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms.users.contract.ServiceKafka;
import social.network.ms.users.contract.ServiceLogging;
import social.network.ms.users.model.dto.NotificationDto;

import java.util.UUID;

@Service
public class ServiceKafkaImpl implements ServiceKafka {

    private final ServiceLogging logging;

    @Autowired
    public ServiceKafkaImpl(ServiceLogging logging) {
        this.logging = logging;
    }

    @Override
    public UUID sendNotification(NotificationDto notification) {
        logging.printInfo("Notifications service: send notification: " + notification);
        return null;
    }

    @Override
    public NotificationDto readNotification(UUID key) {
        return null;
    }
}