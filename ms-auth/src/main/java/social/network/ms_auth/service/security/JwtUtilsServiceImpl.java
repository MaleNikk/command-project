package social.network.ms_auth.service.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.service.model.JwtUserDetails;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtUtilsServiceImpl implements JwtUtilsService {
    private final ApplicationLogger logger;

    private final ObjectMapper mapper;

    private final String jwtSecret;

    public JwtUtilsServiceImpl(
            @Autowired
            ApplicationLogger logger,
            @Autowired
            ObjectMapper mapper,
            @Value("${app.jwt.secret}")
            String jwtSecret) {
        this.logger = logger;
        this.mapper = mapper;
        this.jwtSecret = jwtSecret;
    }

    @Override
    public String generateJwtToken(JwtUserDetails userDetailsJwt, Duration tokenExpiration) {
        return Jwts
                .builder()
                .setSubject(getSubject(userDetailsJwt))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(tokenExpiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public JwtUserDetails getUserData(String token) {
        return getUserDetails(Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    @Override
    public boolean validateToken(String authToken) {
        boolean check = false;
        try {
            JwtUserDetails userDetailsJwt = getUserData(authToken);
            check = userDetailsJwt != null;
        } catch (SignatureException exception) {
            logger.printLog(String.format("Invalid signature: %s", exception.getMessage()), Level.ERROR);
        } catch (MalformedJwtException exception) {
            logger.printLog(String.format("Invalid token: %s", exception.getMessage()), Level.ERROR);
        } catch (ExpiredJwtException exception) {
            logger.printLog(String.format("Token is expired: %s", exception.getMessage()), Level.ERROR);
        } catch (UnsupportedJwtException exception) {
            logger.printLog(String.format("Token is unsupported: %s", exception.getMessage()), Level.ERROR);
        } catch (IllegalArgumentException exception) {
            logger.printLog(String.format("Claims string is empty: %s", exception.getMessage()), Level.ERROR);
        }
        return check;
    }

    private String getSubject(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.printLog("We caught exception with parse object to string!", Level.ERROR);
            throw new RuntimeException(e);
        }
    }

    private JwtUserDetails getUserDetails(String data) {
        try {
            return mapper.readValue(data, JwtUserDetails.class);
        } catch (JsonProcessingException e) {
            logger.printLog("We caught exception with parse data to object!", Level.ERROR);
            throw new RuntimeException(e);
        }

    }
}
