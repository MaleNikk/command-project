package social.network.ms.users.mapper;

import org.mapstruct.Mapper;
import social.network.ms.users.model.dto.*;
import social.network.ms.users.model.entity.AccountEntity;
import social.network.ms.users.model.entity.UpdateEntity;

import java.util.UUID;

@Mapper
public interface UserModelMapper {
    NotificationDto getNotification(ShortAccountDto account, NotificationType notificationType);

    ShortAccountDto shortFrom(AccountEntity accountEntity);

    AccountEntity from(ShortAccountDto account);

    UpdateEntity from(UpdateAccountDto update, UUID id);

    FullAccountDto fullFrom(AccountEntity accountByEmail);

    NotificationDto getNotification(FullAccountDto account, NotificationType notificationType);
}