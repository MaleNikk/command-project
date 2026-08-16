package social.network.ms_auth.service.project;

import social.network.ms_auth.dto.request.*;
import social.network.ms_auth.dto.responce.*;
import social.network.ms_auth.service.model.JwtUserTask;

import java.net.InetAddress;

public interface ApplicationService {
    ValidateResponse validate(ValidateRequest request);
    RegistrationResponse register(RegistrationRequest request, InetAddress address);
    LoginResponse login(LogInRequest request);
    String logout(RefreshTokenRequest request);
    RegistrationResponse edit(ChangePasswordRequest request);
    LoginResponse getRefresh(RefreshTokenRequest request);
    JwtUserTask setTask(TaskRequest request);

    CaptchaResponse getCaptcha();
    RecoveryResponse getRecovery(PasswordRecoveryRequest request);
}