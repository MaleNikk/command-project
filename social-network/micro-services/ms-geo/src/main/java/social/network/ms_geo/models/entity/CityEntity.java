package social.network.ms_geo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    private UUID cityId;
    private boolean isDeleted;
    private String cityTitle;
    private UUID countryId;
    private String countryTitle;
}