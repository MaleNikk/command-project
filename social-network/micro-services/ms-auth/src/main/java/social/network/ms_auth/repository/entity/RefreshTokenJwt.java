package social.network.ms_auth.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash("refresh")
public class RefreshTokenJwt {

    @Id
    @Indexed
    private UUID id;

    @Indexed
    private String refresh;

    public RefreshTokenJwt() {
    }

    public RefreshTokenJwt(UUID id, String refresh) {
        this.id = id;
        this.refresh = refresh;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}