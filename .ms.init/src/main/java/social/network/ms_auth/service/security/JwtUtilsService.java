package social.network.ms_auth.service.security;

import social.network.ms_auth.service.model.JwtUserDetails;

import java.time.Duration;

public interface JwtUtilsService {

    String generateJwtToken(JwtUserDetails userDetailsJwt, Duration tokenExpiration);

    JwtUserDetails getUserData(String token);

    boolean validateToken(String authToken);
}
