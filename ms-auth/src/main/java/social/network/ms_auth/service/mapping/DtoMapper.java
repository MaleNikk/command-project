package social.network.ms_auth.service.mapping;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import social.network.ms_auth.repository.entity.*;
import social.network.ms_auth.dto.request.RegistrationRequest;
import social.network.ms_auth.service.model.AccessTokenModel;
import social.network.ms_auth.service.model.JwtUserDetails;
import social.network.ms_auth.service.model.JwtUserTask;
import social.network.ms_auth.service.model.RefreshTokenModel;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class DtoMapper {

    public static UserJwt from(RegistrationRequest request, PasswordEncoder encoder) {
        UUID userId = UUID.randomUUID();
        return new UserJwt(
                userId,
                request.email(),
                encoder.encode(request.password1()),
                Set.of(new UserRole(RoleType.USER)),
                Set.of());
    }

    public static JwtUserDetails from(UserJwt user) {
        return new JwtUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()),
                user.getIdTasks().stream().map(DtoMapper::from).collect(Collectors.toSet()));
    }

    public static UserJwt from(JwtUserDetails details) {
        return new UserJwt(
                details.id(),
                details.email(),
                details.password(),
                details.roles().stream().map(DtoMapper::from).collect(Collectors.toSet()),
                details.tasks().stream().map(DtoMapper::from).collect(Collectors.toSet()));
    }

    public static UserTask from(JwtUserTask userTask) {
        return new UserTask(
                userTask.id(),
                userTask.userId(),
                userTask.start(),
                userTask.finish(),
                userTask.task(),
                userTask.timeRelease()
                );
    }

    public static UserRole from(String role) {
        return new UserRole(RoleType.valueOf(role));
    }

    public static JwtUserTask from(UserTask task) {
        return new JwtUserTask(
                task.getId(),
                task.getUserId(),
                task.getStart(),
                task.getFinish(),
                Instant.now(),
                task.getTask(),
                task.getTimeRelease());
    }

    public static AccessTokenModel from(AccessTokenJwt tokenJwt) {
        return new AccessTokenModel(tokenJwt.getAccess());
    }

    public static RefreshTokenModel from(RefreshTokenJwt tokenJwt) { return new RefreshTokenModel(tokenJwt.getRefresh()); }
}