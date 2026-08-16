package social.network.ms_account.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms_account.logging.ApplicationLogging;
import social.network.ms_account.model.dto.NotificationDto;

@Service
public class NotificationServiceImpl implements NotificationsService {

    private final ApplicationLogging logging;

    public NotificationServiceImpl(
            @Autowired
            ApplicationLogging logging) {
        this.logging = logging;
    }

    @Override
    public void sendNotification(NotificationDto notification) {
        logging.printInfo("Notifications service: send notification: " + notification);
    }
}