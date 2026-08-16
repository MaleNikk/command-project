package social.network.ms_geo.storage.postgres;

import social.network.ms_geo.models.entity.CityEntity;
import social.network.ms_geo.models.entity.CountryEntity;

import java.util.List;
import java.util.UUID;

public interface GeoRepositoryPostgres {
    List<CountryEntity> getCountries();

    List<CityEntity> getCities(UUID id);
}