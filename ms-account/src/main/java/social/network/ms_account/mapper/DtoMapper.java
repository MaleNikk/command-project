package social.network.ms_account.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import social.network.ms_account.model.dto.*;
import social.network.ms_account.model.entity.AccountEntity;
import social.network.ms_account.model.entity.UpdateEntity;

import java.util.UUID;

public final class DtoMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static NotificationDto getNotification(Object object, NotificationType type) {
        String data;
        try {
            data = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new NotificationDto(type, data);
    }

    public static UpdateEntity from(UpdateAccountDto update, UUID id) {
        return new UpdateEntity(
                id,
                update.firstName(),
                update.lastName(),
                update.birthDate(),
                update.phone(),
                update.about(),
                update.city(),
                update.country(),
                update.emojiStatus(),
                update.photo(),
                update.profileCover()

        );
    }


    public static AccountEntity from(ShortAccountDto account) {
        return new AccountEntity(
                UUID.randomUUID(),
                account.firstName(),
                account.lastName(),
                account.email(),
                account.password(),
                AccountRole.USER,
                account.phone(),
                account.photo(),
                account.profileCover(),
                account.about(),
                account.city(),
                account.country(),
                account.statusCode().name(),
                null,
                account.birthDate(),
                account.messagePermission(),
                null,
                account.emojiStatus(),
                null,
                null,
                null,
                Boolean.FALSE,
                Boolean.FALSE,
                Boolean.FALSE
        );
    }

    public static ShortAccountDto shortFrom(AccountEntity entity) {
        return new ShortAccountDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhone(),
                entity.getPhoto(),
                entity.getProfileCover(),
                entity.getAbout(),
                entity.getCity(),
                entity.getCountry(),
                StatusCode.valueOf(entity.getStatusCode()),
                entity.getRegDate(),
                entity.getBirthDate(),
                entity.getMessagePermission(),
                entity.getLastOnlineTime(),
                entity.getEmojiStatus(),
                entity.getCreatedOn(),
                entity.getUpdatedOn(),
                entity.getDeletionTimestamp(),
                entity.isDeleted(),
                entity.isBlocked(),
                entity.isOnline()
        );
    }

    public static FullAccountDto fullFrom(AccountEntity entity) {
        return new FullAccountDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.getPhone(),
                entity.getPhoto(),
                entity.getProfileCover(),
                entity.getAbout(),
                entity.getCity(),
                entity.getCountry(),
                StatusCode.valueOf(entity.getStatusCode()),
                entity.getRegDate(),
                entity.getBirthDate(),
                entity.getMessagePermission(),
                entity.getLastOnlineTime(),
                entity.getEmojiStatus(),
                entity.getCreatedOn(),
                entity.getUpdatedOn(),
                entity.getDeletionTimestamp(),
                entity.isDeleted(),
                entity.isBlocked(),
                entity.isOnline());
    }
}
