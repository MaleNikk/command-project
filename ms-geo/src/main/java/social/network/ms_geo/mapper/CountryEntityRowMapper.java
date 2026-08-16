package social.network.ms_geo.mapper;

import org.springframework.stereotype.Component;
import social.network.ms_geo.models.entity.CountryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CountryEntityRowMapper implements GeoEntityRowMapper<CountryEntity> {
    public CountryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CountryEntity((UUID)rs.getObject("country_id"), rs.getBoolean("is_deleted"), rs.getString("country_title"));
    }
}