package social.network.ms_geo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    private UUID countryId;
    private boolean isDeleted;
    private String countryTitle;
}