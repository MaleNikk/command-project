package social.network.ms.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;

/**
 * Configuration: SQL repositories
 */

@Configuration
@EnableJdbcRepositories
public class ConfigDataStore implements DataSourceFactory {
    @Override
    public ConnectionProperties getConnectionProperties() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }
}