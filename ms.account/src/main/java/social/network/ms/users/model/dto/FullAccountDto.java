package social.network.ms.users.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record FullAccountDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String password,
        AccountRole role,
        String phone,
        String photo,
        String profileCover,
        String about,
        String city,
        String country,
        StatusCode statusCode,
        LocalDateTime regDate,
        LocalDateTime birthDate,
        String messagePermission,
        LocalDateTime lastOnlineTime,
        String emojiStatus,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        LocalDateTime deletionTimestamp,
        boolean deleted,
        boolean blocked,
        boolean isOnline
) {}