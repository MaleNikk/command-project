package social.network.ms_dialogs.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import social.network.ms_dialogs.dto.ValidateTokenRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.logging.Logger;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Value("${application.security.URI}")
    private String pathSecurity;
    private final Base64.Decoder decoder = Base64.getUrlDecoder();
    private final RestClient restClient = RestClient.create();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = Logger.getLogger("Security service logging.");

    public String getUserId(HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        return token == null ? null : (this.checkToken(this.pathSecurity, ValidateTokenRequest.getInstance(token)) ? this.deserialize(token) : null);
    }

    private String deserialize(String token) {
        if (!token.contains(".")) {
            token = new String(this.decoder.decode(token));
        }

        String[] data = (new String(this.decoder.decode(token.split("\\.")[1]))).split("\"");
        String userId = null;

        for(int i = 0; i < data.length; ++i) {
            if (data[i].toLowerCase().contains("sub")) {
                userId = data[i + 2];
                break;
            }
        }

        return userId;
    }

    private boolean checkToken(String pathUri, ValidateTokenRequest tokenRequestDto) {
        return Boolean.TRUE;
    }

    private boolean checkConnection(String path) {
        try {
            return (new URI(path)).isAbsolute();
        } catch (URISyntaxException e) {
            this.logger.info("Connection with security service not available.");
            throw new RuntimeException(e);
        }
    }
}
