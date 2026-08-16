package social.network.ms_auth.dto.request;

import java.time.Instant;

public record ValidateRequest(String token, String serviceId, Instant timeSend) {
}