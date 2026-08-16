//package social.network.ms_auth.configuration.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.event.Level;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import social.network.ms_auth.logging.ApplicationLogger;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Configuration
//public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {
//
//    private final ApplicationLogger logger;
//
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public AuthenticationEntryPointJwt(ApplicationLogger logger) {
//        this.logger = logger;
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public void commence(
//            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
//    ) throws IOException {
//        logger.printLog(String.format("Unauthorised error: %s", authException.getMessage()), Level.ERROR);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        Map<String, Object> body = Map.of(
//        "status", HttpServletResponse.SC_UNAUTHORIZED,
//        "error", "Unauthorised",
//        "message", authException.getMessage(),
//        "path", request.getServletPath());
//        objectMapper.writeValue(response.getOutputStream(), body);
//    }
//}