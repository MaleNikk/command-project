package social.network.ms_dialogs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import social.network.ms_dialogs.logger.ApplicationLogging;

import javax.sql.DataSource;

@Configuration
public class ApplicationDataSourceConfig {
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private ApplicationLogging logger;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        this.logger.printLog("DataSourceConfig class. Init jdbcTemplate.");
        DataSource dataSource = DataSourceBuilder
                .create().url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
        return new JdbcTemplate(dataSource);
    }
}
