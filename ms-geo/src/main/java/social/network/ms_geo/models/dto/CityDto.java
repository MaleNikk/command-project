package social.network.ms_geo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto implements Comparable<CityDto> {
    private UUID countryId;
    private UUID id;
    private Boolean isDeleted;
    private String title;

    public int compareTo(CityDto o) {
        boolean check1 = this.getTitle().substring(0, 1).toLowerCase().getBytes()[0] > o.getTitle().substring(0, 1).toLowerCase().getBytes()[0];
        boolean check2 = this.getTitle().substring(0, 1).toLowerCase().getBytes()[0] < o.getTitle().substring(0, 1).toLowerCase().getBytes()[0];
        if (check1) {
            return 1;
        } else {
            return check2 ? -1 : 0;
        }
    }
}