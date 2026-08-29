package social.network.ms.users.model.dto;

import java.time.LocalDateTime;

public record UpdateAccountDto(
        String firstName,
        String lastName,
        LocalDateTime birthDate,
        String phone,
        String about,
        String city,
        String country,
        String emojiStatus,
        String photo,
        String profileCover) {
}