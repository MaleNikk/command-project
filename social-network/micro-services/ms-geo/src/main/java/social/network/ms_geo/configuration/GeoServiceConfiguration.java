package social.network.ms_geo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import social.network.ms_geo.filling.FillingDataLocation;
import social.network.ms_geo.mapper.CityEntityRowMapper;
import social.network.ms_geo.mapper.CountryEntityRowMapper;
import social.network.ms_geo.storage.memory.GeoRepositoryInMemory;
import social.network.ms_geo.storage.memory.GeoRepositoryInMemoryImpl;
import social.network.ms_geo.storage.postgres.GeoRepositoryPostgres;
import social.network.ms_geo.storage.postgres.GeoRepositoryPostgresImpl;

@Configuration
public class GeoServiceConfiguration {
    private final JdbcTemplate jdbcTemplate;
    private final CityEntityRowMapper cityMapper;
    private final CountryEntityRowMapper countryMapper;
    private final FillingDataLocation fillingDataLocation;

    public GeoServiceConfiguration(
            @Autowired JdbcTemplate jdbcTemplate,
            @Autowired CityEntityRowMapper cityMapper,
            @Autowired CountryEntityRowMapper countryMapper,
            @Autowired FillingDataLocation fillingDataLocation) {
        this.jdbcTemplate = jdbcTemplate;
        this.cityMapper = cityMapper;
        this.countryMapper = countryMapper;
        this.fillingDataLocation = fillingDataLocation;
    }

    @Bean
    public GeoRepositoryPostgres repositoryPostgres() {
        return new GeoRepositoryPostgresImpl(jdbcTemplate, countryMapper, cityMapper);
    }

    @Bean
    public GeoRepositoryInMemory repositoryInMemory() {
        return new GeoRepositoryInMemoryImpl(repositoryPostgres(), fillingDataLocation);
    }
}