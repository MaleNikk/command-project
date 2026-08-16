package social.network.ms_auth.service.project;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import social.network.ms_auth.dto.request.*;
import social.network.ms_auth.dto.responce.*;
import social.network.ms_auth.exception.AlreadyExistsException;
import social.network.ms_auth.kafka.KafkaService;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.repository.entity.UserJwt;
import social.network.ms_auth.service.mapping.DtoMapper;
import social.network.ms_auth.service.model.AccessTokenModel;
import social.network.ms_auth.service.model.JwtUserTask;
import social.network.ms_auth.service.model.RefreshTokenModel;
import social.network.ms_auth.service.security.AccessTokenService;
import social.network.ms_auth.service.security.RefreshTokenService;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Date;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationLogger logger;

    private final AccessTokenService accessTokenService;

    private final RefreshTokenService refreshTokenService;

    private final KafkaService kafkaService;

    private final PasswordEncoder encoder;

    @Autowired
    public ApplicationServiceImpl(
            ApplicationLogger logger,
            AccessTokenService accessTokenService,
            RefreshTokenService refreshTokenService,
            KafkaService kafkaService,
            PasswordEncoder encoder) {
        this.logger = logger;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.kafkaService = kafkaService;
        this.encoder = encoder;
    }

    @Override
    public ValidateResponse validate(ValidateRequest request) {
        return null;
    }

    @Override
    public RegistrationResponse register(RegistrationRequest request, InetAddress address) {
        logger.printLog("Call application service method: register new account.", Level.INFO);
        UserJwt userJwt = DtoMapper.from(request, encoder);
        if (accessTokenService.checkRegistry(userJwt)) {
            throw new AlreadyExistsException("Email already exists");
        } else {
            logger.printLog("Register new account complete successfully!", Level.INFO);
            kafkaService.sendNotification(
                    new NotificationRegisterUser(
                            userJwt.getId(),
                            request.firstname(),
                            request.lastname(),
                            request.email(),
                            Date.from(Instant.now()),
                            null,
                            address
                    ).toString());
            return new RegistrationResponse(accessTokenService.generateAccessToken(userJwt));
        }
    }

    @Override
    public LoginResponse login(LogInRequest request) {
        logger.printLog("Call application service method: login account.", Level.INFO);
        AccessTokenModel accessTokenModel = accessTokenService.getRegistration(request.email());
        RefreshTokenModel refreshTokenModel = refreshTokenService.generateRefreshToken(accessTokenModel);
        return new LoginResponse(accessTokenModel.access(), refreshTokenModel.refresh());
    }

    @Override
    public String logout(RefreshTokenRequest request) {
        logger.printLog("Call application service method: logout account.", Level.INFO);
        RefreshTokenModel model = refreshTokenService.deleteRefreshToken(request);
        return MessageFormat.format("Refresh token: {0} deactivated!", model.refresh());
    }

    @Override
    public RegistrationResponse edit(ChangePasswordRequest request) {
        logger.printLog("Call application service method: edit account.", Level.INFO);
        return null;
    }

    @Override
    public LoginResponse getRefresh(RefreshTokenRequest request) {
        logger.printLog("Call application service method: get refresh token for account.", Level.INFO);
        return null;
    }

    @Override
    public JwtUserTask setTask(TaskRequest request) {
        return null;
    }

    @Override
    public CaptchaResponse getCaptcha() {
        return null;
    }

    @Override
    public RecoveryResponse getRecovery(PasswordRecoveryRequest request) {
        return null;
    }
}