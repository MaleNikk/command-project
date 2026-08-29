package social.network.ms_auth.repository.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserRole implements GrantedAuthority {

    private RoleType role;

    public UserRole() {
    }

    public UserRole(RoleType role) {
        this.role = role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}