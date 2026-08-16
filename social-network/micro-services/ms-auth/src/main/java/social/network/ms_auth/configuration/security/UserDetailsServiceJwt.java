package social.network.ms_auth.configuration.security;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import social.network.ms_auth.logging.ApplicationLogger;
import social.network.ms_auth.repository.AccessTokenRepository;
import social.network.ms_auth.service.mapping.DtoMapper;
import social.network.ms_auth.service.security.JwtUtilsService;

@Configuration
public class UserDetailsServiceJwt implements UserDetailsService {

    private final ApplicationLogger logger;

    private final JwtUtilsService service;

    private final AccessTokenRepository repository;

    @Autowired
    public UserDetailsServiceJwt(ApplicationLogger logger, JwtUtilsService service, AccessTokenRepository repository) {
        this.logger = logger;
        this.service = service;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.printLog("Call method register user by email: " + email, Level.INFO);
        return DtoMapper.from(service.getUserData(repository.findByEmail(email).getAccess()));
    }
}