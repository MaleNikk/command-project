package social.network.ms_geo.filling;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import social.network.ms_geo.models.entity.CityEntity;
import social.network.ms_geo.models.entity.CountryEntity;
import social.network.ms_geo.storage.postgres.GeoRepositoryPostgres;
import social.network.ms_geo.storage.postgres.QueriesConstant;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class FillingDataLocation {
    private final String pathData;
    private final GeoRepositoryPostgres repositoryPostgres;
    private final JdbcTemplate jdbcTemplate;

    public FillingDataLocation(
            @Value("${location.geo.data}") String pathData,
            @Autowired GeoRepositoryPostgres repositoryPostgres,
            @Autowired JdbcTemplate jdbcTemplate) {
        this.pathData = pathData;
        this.repositoryPostgres = repositoryPostgres;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void fill() {
        HashMap<?, ?> countries = new HashMap<>();
        StringBuilder dataCountries = new StringBuilder(QueriesConstant.SAVE_COUNTRY);
        StringBuilder dataCities = new StringBuilder(QueriesConstant.SAVE_CITY);
        ObjectMapper mapper = new ObjectMapper(JsonFactory.builder()
                .configure(JsonWriteFeature.COMBINE_UNICODE_SURROGATES_IN_UTF8, Boolean.TRUE).build());

        try {
            countries = mapper.readValue(new File(this.pathData), countries.getClass());
            HashMap<String, Set<String>> saved = this.initDataBase();
            countries.forEach((key, value) -> {
                UUID countryId = UUID.randomUUID();
                UUID cityId = UUID.randomUUID();
                String country = String.valueOf(key);
                List<String> cities = (List<String>)value;
                if (!saved.containsKey(country)) {
                    saved.put(country, new HashSet<>());
                    dataCountries.append("('")
                            .append(countryId).append("','")
                            .append(false).append("','")
                            .append(country).append("'),");
                }

                cities.forEach((city) -> {
                    if (!(saved.get(country)).contains(city)) {
                        dataCities.append("('")
                                .append(cityId).append("','")
                                .append(false).append("','")
                                .append(city).append("','")
                                .append(countryId).append("','")
                                .append(country).append("'),");
                        (saved.get(country)).add(city);
                    }

                });
                if (!(saved.get(country)).containsAll(cities)) {
                    (saved.get(country)).addAll(cities);
                }

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Objects.equals(dataCountries.charAt(dataCountries.length() - 1), ',')) {
            jdbcTemplate.update(dataCountries.deleteCharAt(dataCountries.lastIndexOf(",")).append(';').toString());
        }

        if (Objects.equals(dataCities.charAt(dataCities.length() - 1), ',')) {
            jdbcTemplate.update(dataCities.deleteCharAt(dataCities.lastIndexOf(",")).append(';').toString());
        }

    }

    private HashMap<String, Set<String>> initDataBase() {
        HashMap<String, Set<String>> countriesData = new HashMap<>();
        List<CountryEntity> countryEntities = repositoryPostgres.getCountries();
        if (!countryEntities.isEmpty()) {
            countryEntities.forEach((country) -> {
                List<CityEntity> cities = repositoryPostgres.getCities(country.getCountryId());
                countriesData.put(country.getCountryTitle(), new HashSet<>(cities.stream().map(CityEntity::getCityTitle).toList()));
            });
        }
        return countriesData;
    }
}