package social.network.ms_auth.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "tasks")
public class UserTask {

    @Id
    private UUID id;

    private UUID userId;

    private Instant start;

    private Instant finish;

    private String task;

    private Duration timeRelease;

    public UserTask() {
    }

    public UserTask(UUID id, UUID userId, Instant start, Instant finish, String task, Duration timeRelease) {
        this.id = id;
        this.userId = userId;
        this.start = start;
        this.finish = finish;
        this.task = task;
        this.timeRelease = timeRelease;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getStart() {
        return start;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getFinish() {
        return finish;
    }

    public void setFinish(Instant finish) {
        this.finish = finish;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Duration getTimeRelease() {
        return timeRelease;
    }

    public void setTimeRelease(Duration timeRelease) {
        this.timeRelease = timeRelease;
    }
}
