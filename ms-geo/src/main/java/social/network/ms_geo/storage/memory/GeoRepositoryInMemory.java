package social.network.ms_geo.storage.memory;

import social.network.ms_geo.models.dto.CountryDto;

import java.util.Map;
import java.util.UUID;

public interface GeoRepositoryInMemory {
    Map<?, ?> load();

    Map<UUID, CountryDto> getCountries();
}