package social.network.ms_dialogs.exception.management;

import org.springframework.stereotype.Component;

@Component
public class AppErrorResponseBody {
    private String message;
    private String description;

    public AppErrorResponseBody() {
    }

    public AppErrorResponseBody(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static AppErrorResponseBody getInstance(String message, String description) {
        return new AppErrorResponseBody(message, description);
    }
}
