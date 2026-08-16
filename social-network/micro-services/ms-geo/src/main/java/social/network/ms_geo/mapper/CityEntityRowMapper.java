package social.network.ms_geo.mapper;

import org.springframework.stereotype.Component;
import social.network.ms_geo.models.entity.CityEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CityEntityRowMapper implements GeoEntityRowMapper<CityEntity> {
    public CityEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CityEntity((UUID)rs.getObject("city_id"), rs.getBoolean("is_deleted"), rs.getString("city_title"), (UUID)rs.getObject("country_id"), rs.getString("country_title"));
    }
}