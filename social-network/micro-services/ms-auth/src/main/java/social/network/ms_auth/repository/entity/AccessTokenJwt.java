package social.network.ms_auth.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "access")
public class AccessTokenJwt {

    @Id
    private UUID id;

    private String email;

    private String access;

    public AccessTokenJwt() {
    }

    public AccessTokenJwt(UUID id, String email, String access) {
        this.id = id;
        this.email = email;
        this.access = access;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}