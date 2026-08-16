package social.network.ms_auth.dto.request;

import java.util.UUID;

public record ChangePasswordRequest(UUID id, String password1, String password2) {
}