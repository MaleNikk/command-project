package social.network.ms_auth.handler;

public class ErrorResponseBody {

    private String message;

    private String description;

    public ErrorResponseBody() {
    }

    public ErrorResponseBody(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
