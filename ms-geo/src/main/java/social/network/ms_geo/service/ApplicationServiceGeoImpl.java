package social.network.ms_geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms_geo.models.dto.CityDto;
import social.network.ms_geo.models.dto.CountryDto;
import social.network.ms_geo.storage.memory.GeoRepositoryInMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ApplicationServiceGeoImpl implements ApplicationServiceGeo {
    private final GeoRepositoryInMemory repositoryInMemory;
    private final Logger logger;

    public ApplicationServiceGeoImpl(@Autowired GeoRepositoryInMemory repositoryInMemory) {
        this.repositoryInMemory = repositoryInMemory;
        this.logger = Logger.getLogger("Application geo service component.");
    }

    @Override
    public Map<?, ?> load() {
        logger.info("Call to method load.");
        return repositoryInMemory.load();
    }

    @Override
    public List<CityDto> getCities(String countryId) {
        if (repositoryInMemory.getCountries().isEmpty()) {
            load();
        }

        return (repositoryInMemory.getCountries().get(UUID.fromString(countryId))).getCities().stream().toList();
    }

    @Override
    public List<CountryDto> getCountries() {
        if (repositoryInMemory.getCountries().isEmpty()) {
            load();
        }
        return new ArrayList<>(repositoryInMemory.getCountries().values().stream().sorted().toList());
    }
}