package social.network.ms_auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.ms_auth.repository.entity.RefreshTokenJwt;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepositoryJwt extends CrudRepository<RefreshTokenJwt, UUID> {

    Optional<RefreshTokenJwt> findByToken(String token);
}