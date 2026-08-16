package social.network.ms_notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms_notifications.model.entity.NotificationEntity;
import social.network.ms_notifications.logging.ApplicationLogging;
import social.network.ms_notifications.storage.NotificationStorage;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private final NotificationStorage notificationStorage;

    private final ApplicationLogging logging;

    @Autowired
    public NotificationServiceImpl(NotificationStorage notificationStorage,
                                   ApplicationLogging logging) {
        this.notificationStorage = notificationStorage;
        this.logging = logging;
    }

    public boolean sendNotification(NotificationEntity entity){
        logging.printInfo("Notifications service: call method push content to storage");
        return notificationStorage.sendNotification(entity);
    }
}