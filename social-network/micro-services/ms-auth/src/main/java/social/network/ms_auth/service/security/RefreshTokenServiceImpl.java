package social.network.ms_auth.service.security;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import social.network.ms_auth.dto.request.RefreshTokenRequest;
import social.network.ms_auth.exception.EntityNotFoundException;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.repository.RefreshTokenRepositoryJwt;
import social.network.ms_auth.repository.entity.RefreshTokenJwt;
import social.network.ms_auth.service.mapping.DtoMapper;
import social.network.ms_auth.service.model.AccessTokenModel;
import social.network.ms_auth.service.model.JwtUserDetails;
import social.network.ms_auth.service.model.RefreshTokenModel;

import java.time.Duration;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final Duration refreshTokenExpiration;

    private final RefreshTokenRepositoryJwt repository;

    private final JwtUtilsService service;

    private final ApplicationLogger logger;

    public RefreshTokenServiceImpl(
            @Value("${app.jwt.refreshTokenExpiration}")
            Duration refreshTokenExpiration,
            @Autowired
            RefreshTokenRepositoryJwt repository,
            @Autowired
            JwtUtilsService service,
            @Autowired
            ApplicationLogger logger) {
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.repository = repository;
        this.service = service;
        this.logger = logger;
    }

    @Override
    public RefreshTokenModel generateRefreshToken(AccessTokenModel model) {
        logger.printLog("Generate refresh token for access token: " + model.access(), Level.INFO);
        JwtUserDetails userDetails = service.getUserData(model.access());
        return DtoMapper.from(repository.save(new RefreshTokenJwt(userDetails.id(), service.generateJwtToken(userDetails, refreshTokenExpiration))));
    }

    @Override
    public boolean checkRefreshToken(RefreshTokenModel model) {
        logger.printLog("Check refresh token: " + model.refresh(), Level.INFO);
        return service.validateToken(model.refresh());
    }

    @Override
    public RefreshTokenModel deleteRefreshToken(RefreshTokenRequest request) {
        logger.printLog("Delete refresh token: " + request.refreshToken(), Level.INFO);
        RefreshTokenJwt model = repository.findByToken(request.refreshToken()).orElseThrow(new EntityNotFoundException("Refresh token not found!"));
        repository.deleteById(model.getId());
        return DtoMapper.from(model);
    }

    @Override
    public JwtUserDetails getUserDetails(RefreshTokenModel model) {
        logger.printLog("Get user details from token: " + model.refresh(), Level.INFO);
        return service.getUserData(model.refresh());
    }
}