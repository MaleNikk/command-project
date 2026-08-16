package social.network.ms_auth.repository.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Component
public class UserJwt implements UserDetails {

    private UUID id;

    private String email;

    private String password;

    private Set<UserRole> roles;

    private Set<UserTask> idTasks;

    public UserJwt(UUID id, String email, String password, Set<UserRole> roles, Set<UserTask> idTasks) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.idTasks = idTasks;
    }

    public UserJwt() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<UserTask> getIdTasks() {
        return idTasks;
    }

    public void setIdTasks(Set<UserTask> idTasks) {
        this.idTasks = idTasks;
    }
}