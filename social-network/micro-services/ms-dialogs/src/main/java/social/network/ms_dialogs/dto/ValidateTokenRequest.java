package social.network.ms_dialogs.dto;

public record ValidateTokenRequest(String token) {
    public static ValidateTokenRequest getInstance(String token) {
        return new ValidateTokenRequest(token);
    }
}