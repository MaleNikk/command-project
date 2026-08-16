package social.network.ms_auth.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import social.network.ms_auth.repository.entity.AccessTokenJwt;

import java.util.UUID;

@Repository
@Primary
public interface AccessTokenRepository extends MongoRepository<AccessTokenJwt, UUID> {

    AccessTokenJwt findByEmail(String email);
}