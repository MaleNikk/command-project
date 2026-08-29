package social.network.ms_auth.network;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms_auth.dto.request.*;
import social.network.ms_auth.dto.responce.*;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.service.project.ApplicationService;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/api/v1/auth")
public class ApplicationController {

    private final ApplicationService service;

    private final ApplicationLogger logger;

    @Autowired
    public ApplicationController(ApplicationService service, ApplicationLogger logger) {
        this.service = service;
        this.logger = logger;
    }

    @PostMapping(value = "/validate")
    public ResponseEntity<ValidateResponse> checkValidToken(@RequestBody ValidateRequest request) {
        logger.printLog("Call method check token!", Level.INFO);
        return ResponseEntity.ok(service.validate(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest dto, @RequestHeader HttpHeaders headers) {
        logger.printLog("Call method register!",Level.INFO);
        try {
            return ResponseEntity.ok(service.register(dto, InetAddress.getByName(headers.getFirst(HttpHeaders.HOST))));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestHeader HttpHeaders headers) {
        logger.printLog("Call method refresh token!", Level.INFO);
        return ResponseEntity.ok(service.getRefresh(new RefreshTokenRequest(headers.getFirst(HttpHeaders.AUTHORIZATION))));
    }

    @PostMapping(value = "password/recovery")
    public ResponseEntity<RecoveryResponse> recovery(@RequestBody PasswordRecoveryRequest request) {
        logger.printLog("Call method recovery password!", Level.INFO);
        return ResponseEntity.ok(service.getRecovery(request));
    }

    @PostMapping(value = "password/recovery/{linkId}")
    public ResponseEntity<RegistrationResponse> recoveryByLink(@RequestBody ChangePasswordRequest request) {
        logger.printLog("Call method recovery by link!", Level.INFO);
        return ResponseEntity.ok(service.edit(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LogInRequest request) {
        logger.printLog("Call method login!", Level.INFO);
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping(value = "/captcha")
    public ResponseEntity<CaptchaResponse> getCaptcha() {
        logger.printLog("Call method init captcha!", Level.INFO);
        return ResponseEntity.ok(service.getCaptcha());
    }
}