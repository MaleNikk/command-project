package social.network.ms_auth.service.security;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import social.network.ms_auth.dto.request.ValidateRequest;
import social.network.ms_auth.exception.EntityNotFoundException;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.repository.AccessTokenRepository;
import social.network.ms_auth.repository.entity.AccessTokenJwt;
import social.network.ms_auth.repository.entity.UserJwt;
import social.network.ms_auth.service.mapping.DtoMapper;
import social.network.ms_auth.service.model.AccessTokenModel;

import java.time.Duration;
import java.util.UUID;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private final ApplicationLogger logger;

    private final AccessTokenRepository repository;

    private final JwtUtilsService service;

    private final Duration tokenExpiration;

    public AccessTokenServiceImpl(
            @Autowired
            ApplicationLogger logger,
            @Autowired
            AccessTokenRepository repository,
            @Autowired
            JwtUtilsService service,
            @Value("${app.jwt.tokenExpiration}")
            Duration tokenExpiration) {
        this.logger = logger;
        this.repository = repository;
        this.service = service;
        this.tokenExpiration = tokenExpiration;
    }

    @Override
    public AccessTokenModel generateAccessToken(UserJwt userJwt) {
        logger.printLog("Generate new access token for user: " + userJwt.getUsername(), Level.INFO);
        return DtoMapper.from(repository.save(new AccessTokenJwt(userJwt.getId(), userJwt.getEmail(), service.generateJwtToken(DtoMapper.from(userJwt),tokenExpiration))));
    }

    @Override
    public AccessTokenModel getRegistration(String email) {
        logger.printLog("Get access token from repository by email: " + email, Level.INFO);
        return DtoMapper.from(repository.findByEmail(email));
    }

    @Override
    public UUID validateAccessToken(ValidateRequest request) {
        logger.printLog("Validate access token: " + request.token(), Level.INFO);
        return service.validateToken(request.token()) ? service.getUserData(request.token()).id() : null;
    }

    @Override
    public AccessTokenModel deleteAccessToken(UUID id) {
        logger.printLog("Delete access token for user id: " + id, Level.INFO);
        AccessTokenModel model = DtoMapper.from(repository.findById(id).orElseThrow(new EntityNotFoundException("Access token not found!")));
        repository.deleteById(id);
        return model;
    }

    @Override
    public AccessTokenModel editAccessToken(UserJwt userJwt) {
        logger.printLog("Edit access token for user: " + userJwt.getUsername(), Level.INFO);
        AccessTokenJwt tokenJwt = repository.findById(userJwt.getId()).orElseThrow(new EntityNotFoundException("Access token not found!"));
        repository.deleteById(userJwt.getId());
        return DtoMapper.from(repository.save(new AccessTokenJwt(userJwt.getId(), userJwt.getEmail(), service.generateJwtToken(DtoMapper.from(userJwt),tokenExpiration))));
    }

    @Override
    public boolean checkRegistry(UserJwt userJwt) {
        logger.printLog("Check is present access token for user: " + userJwt.getUsername(), Level.INFO);
        return repository.findById(userJwt.getId()).isPresent();
    }
}