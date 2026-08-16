package social.network.ms_geo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto implements Comparable<CountryDto> {
    private UUID id;
    private Boolean isDeleted;
    private String title;
    private List<CityDto> cities;

    public int compareTo(CountryDto o) {
        boolean check1 = this.getTitle().substring(0, 1).toLowerCase().getBytes()[0] > o.getTitle().substring(0, 1).toLowerCase().getBytes()[0];
        boolean check2 = this.getTitle().substring(0, 1).toLowerCase().getBytes()[0] < o.getTitle().substring(0, 1).toLowerCase().getBytes()[0];
        if (check1) {
            return 1;
        } else {
            return check2 ? -1 : 0;
        }
    }
}
