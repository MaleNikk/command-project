package social.network.ms_auth.configuration.application;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.support.collections.RedisProperties;
import social.network.ms_auth.repository.entity.RefreshTokenJwt;
import social.network.ms_auth.logging.ApplicationLogger;

import java.time.Duration;
import java.util.Collections;

@Configuration
@EnableRedisRepositories(
        keyspaceConfiguration = ApplicationRedisConfiguration.RefreshTokenKeySpaseConfiguration.class,
        enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class ApplicationRedisConfiguration {


    private final Duration tokenExpiration;

    private final ApplicationLogger logger;

    public ApplicationRedisConfiguration(
            @Value("${app.jwt.tokenExpiration}")
            Duration tokenExpiration,
            @Autowired
            ApplicationLogger logger) {
        this.tokenExpiration = tokenExpiration;
        this.logger = logger;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
        logger.printLog("Init redis configuration!", Level.INFO);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getProperty("host"));
        redisStandaloneConfiguration.setPort(Integer.parseInt(redisProperties.getProperty("port")));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    public class RefreshTokenKeySpaseConfiguration extends KeyspaceConfiguration {
        private static final String REFRESH_TOKEN_KEYSPACE = "refresh_tokens";

        @Override
        protected Iterable<KeyspaceSettings> initialConfiguration() {
            KeyspaceSettings keyspaceSettings = new KeyspaceSettings(RefreshTokenJwt.class, REFRESH_TOKEN_KEYSPACE);
            keyspaceSettings.setTimeToLive(tokenExpiration.toMillis());
            return Collections.singleton(keyspaceSettings);
        }
    }

}
