package social.network.ms_geo.mapper;

import social.network.ms_geo.models.dto.CityDto;
import social.network.ms_geo.models.dto.CountryDto;
import social.network.ms_geo.models.entity.CityEntity;
import social.network.ms_geo.models.entity.CountryEntity;

import java.util.List;

public final class DtoMapper {
    public static CityDto from(CityEntity city) {
        return new CityDto(city.getCountryId(), city.getCityId(), city.isDeleted(), city.getCityTitle());
    }

    public static CountryDto from(CountryEntity country, List<CityEntity> cities) {
        return new CountryDto(country.getCountryId(), country.isDeleted(), country.getCountryTitle(), cities.stream().map(DtoMapper::from).sorted().toList());
    }
}
