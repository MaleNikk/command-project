package social.network.ms_notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import social.network.ms_notifications.model.dto.*;
import social.network.ms_notifications.model.entity.SettingsEntity;
import social.network.ms_notifications.model.entity.UpdateEntity;
import social.network.ms_notifications.logging.ApplicationLogging;
import social.network.ms_notifications.mapping.DtoMapper;
import social.network.ms_notifications.storage.ApplicationStorage;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class ApplicationServiceClass implements ApplicationService {

    private final ApplicationStorage storage;

    private final ApplicationLogging logging;

    private final NotificationService notificationService;

    @Autowired
    public ApplicationServiceClass(
            ApplicationStorage storage,
            ApplicationLogging logging,
            NotificationService notificationService) {
        this.storage = storage;
        this.logging = logging;
        this.notificationService = notificationService;
    }

    @Override
    public boolean sendNotificationToKafka(NotificationDto notification) {
        logging.printInfo("Application service class: call method sent data to kafka.");
        return notificationService.sendNotification(DtoMapper.from(notification));
    }

    @Override
    public NotificationSettingsDto getCurrentSettings(UUID id) {
        logging.printInfo("Application service class: call get current settings.");
        return DtoMapper.from(storage.getSettings(id));
    }

    @Override
    public NotificationSettingsDto updateCurrentSettings(NotificationUpdateDto update) {
        logging.printInfo("Application service class: call method update current settings.");
        return DtoMapper.from(storage.editSettings(DtoMapper.from(update)));
    }

    @Override
    public NotificationStatus onOffNotification(UUID id) {
        logging.printInfo("Application service class: call method enable/disable notification.");
        SettingsEntity entity = storage.getSettings(id);
        storage.editSettings(new UpdateEntity(id, !storage.getSettings(id).enableNotifications(), SettingsType.ENABlE_NOTIFICATIONS));
        return entity.enableNotifications() ? NotificationStatus.DISABLE : NotificationStatus.ENABLE;
    }

    @Override
    public boolean createSettingsForNotifications(UUID id) {
        logging.printInfo("Application service class: call method create settings.");
        return DtoMapper.from(storage.saveSettings(SettingsEntity.getInstance(id))) != null;
    }

    @Override
    public PageModelNotificationsDto getPagesNotifications(UUID id) {
        logging.printInfo("Application service class: call method get pages notifications.");
        return DtoMapper.from(storage.getNotifications(id));
    }

    @Override
    public NotificationCountDto getCountNotification(UUID id) {
        logging.printInfo("Application service class: call method count notifications.");
        return new NotificationCountDto(Date.from(Instant.now()), new NotificationCount(storage.getNotifications(id).size()));
    }
}