package social.network.ms_auth.dto.request;

public record RegistrationRequest(String email, String password1, String password2,
                                  String firstname, String lastname, String captchaCode, String captchaSecret){
}