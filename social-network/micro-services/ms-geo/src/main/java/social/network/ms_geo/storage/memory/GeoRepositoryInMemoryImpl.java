package social.network.ms_geo.storage.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import social.network.ms_geo.filling.FillingDataLocation;
import social.network.ms_geo.mapper.DtoMapper;
import social.network.ms_geo.models.dto.CountryDto;
import social.network.ms_geo.storage.postgres.GeoRepositoryPostgres;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Qualifier
@Repository
public class GeoRepositoryInMemoryImpl implements GeoRepositoryInMemory {
    private final Map<UUID, CountryDto> dataCountries;
    private final GeoRepositoryPostgres repositoryPostgres;
    private final FillingDataLocation fillingDataLocation;
    private final Logger logger;

    public GeoRepositoryInMemoryImpl(
            @Autowired GeoRepositoryPostgres repositoryPostgres,
            @Autowired FillingDataLocation fillingDataLocation) {
        this.repositoryPostgres = repositoryPostgres;
        this.fillingDataLocation = fillingDataLocation;
        this.dataCountries = new ConcurrentHashMap<>();
        this.logger = Logger.getLogger("Geo repository in memory.");
    }

    public Map<?, ?> load() {
        this.logger.info("Call method load.");
        this.dataCountries.clear();
        this.fillingDataLocation.fill();
        this.repositoryPostgres.getCountries().forEach((country) ->
                this.dataCountries.put(country.getCountryId(),
                        DtoMapper.from(country, this.repositoryPostgres.getCities(country.getCountryId()))));
        return Map.of("message", "Successful operation");
    }

    public Map<UUID, CountryDto> getCountries() {
        this.logger.info("Call method get countries.");
        return this.dataCountries;
    }
}