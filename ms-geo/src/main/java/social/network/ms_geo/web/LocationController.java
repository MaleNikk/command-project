package social.network.ms_geo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms_geo.models.dto.CityDto;
import social.network.ms_geo.models.dto.CountryDto;
import social.network.ms_geo.service.ApplicationServiceGeo;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping({"api/v1/geo/country"})
public class LocationController {
    private final ApplicationServiceGeo serviceGeo;
    private final Logger logger;

    public LocationController(@Autowired ApplicationServiceGeo serviceGeo) {
        this.serviceGeo = serviceGeo;
        this.logger = Logger.getLogger("Location controller.");
    }

    @PutMapping({"/load"})
    public ResponseEntity<Map<?, ?>> loadGeoData() {
        logger.info("Call method load location data!");
        return ResponseEntity.ok(serviceGeo.load());
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> getCountries() {
        logger.info("Call method get countries!");
        return ResponseEntity.ok(serviceGeo.getCountries());
    }

    @GetMapping({"/{countryId}/city"})
    public ResponseEntity<List<CityDto>> getCitiesByCountry(@PathVariable String countryId) {
        logger.info("Call method get cities by country!");
        return ResponseEntity.ok(serviceGeo.getCities(countryId));
    }
}