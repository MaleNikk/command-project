package social.network.ms_geo.service;

import social.network.ms_geo.models.dto.CityDto;
import social.network.ms_geo.models.dto.CountryDto;

import java.util.List;
import java.util.Map;

public interface ApplicationServiceGeo {
    Map<?, ?> load();

    List<CityDto> getCities(String countryId);

    List<CountryDto> getCountries();
}