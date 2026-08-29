package social.network.ms_auth.configuration.security;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import social.network.ms_auth.logging.ApplicationLogger;

@Configuration
@EnableMethodSecurity
public class ApplicationSecurityConfiguration {

    private final ApplicationLogger logger;

    private final UserDetailsServiceJwt userDetailsServiceJwt;

    //private final AuthenticationEntryPointJwt entryPointJwt;

    @Autowired
    public ApplicationSecurityConfiguration(ApplicationLogger logger, UserDetailsServiceJwt userDetailsServiceJwt) {
        this.logger = logger;
        this.userDetailsServiceJwt = userDetailsServiceJwt;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.printLog("Initialize password encoder.", Level.INFO);
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        logger.printLog("Initialize DAO authentication provider.", Level.INFO);
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsServiceJwt);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        logger.printLog("Initialize authentication manager.", Level.INFO);
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        logger.printLog("Initialize security filter chain!", Level.INFO);
//        httpSecurity.authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(config -> config.authenticationEntryPoint(entryPointJwt))
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(httpSessionManager -> httpSessionManager
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider());
//        return httpSecurity.build();
//    }
}
