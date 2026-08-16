package social.network.ms_auth.service.security;

import social.network.ms_auth.dto.request.ValidateRequest;
import social.network.ms_auth.repository.entity.UserJwt;
import social.network.ms_auth.service.model.AccessTokenModel;

import java.util.UUID;

public interface AccessTokenService {

    AccessTokenModel generateAccessToken(UserJwt userJwt);

    AccessTokenModel getRegistration(String email);

    UUID validateAccessToken(ValidateRequest request);

    AccessTokenModel deleteAccessToken(UUID id);

    AccessTokenModel editAccessToken(UserJwt userJwt);

    boolean checkRegistry(UserJwt userJwt);
}