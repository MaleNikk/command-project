package social.network.ms_geo.storage.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import social.network.ms_geo.mapper.GeoEntityRowMapper;
import social.network.ms_geo.models.entity.CityEntity;
import social.network.ms_geo.models.entity.CountryEntity;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Primary
@Repository
public class GeoRepositoryPostgresImpl implements GeoRepositoryPostgres {
    private final JdbcTemplate jdbcTemplate;
    private final GeoEntityRowMapper<CountryEntity> countryMapper;
    private final GeoEntityRowMapper<CityEntity> cityMapper;
    private final Logger logger;

    public GeoRepositoryPostgresImpl(
            @Autowired JdbcTemplate jdbcTemplate,
            @Autowired GeoEntityRowMapper<CountryEntity> countryMapper,
            @Autowired GeoEntityRowMapper<CityEntity> cityMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.countryMapper = countryMapper;
        this.cityMapper = cityMapper;
        this.logger = Logger.getLogger("Geo repository postgres component.");
    }

    @Override
    public List<CountryEntity> getCountries() {
        logger.info("Call to method get all countries from data base.");
        return jdbcTemplate.query(QueriesConstant.GET_ALL_COUNTRIES, countryMapper);
    }

    @Override
    public List<CityEntity> getCities(UUID countryId) {
        logger.info("Call to method get all cities by country id from data base.");
        return jdbcTemplate.query(QueriesConstant.GET_ALL_CITIES, cityMapper, countryId);
    }
}
