package pe.com.agencymicroservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TripDto {
    private int agencyId;
    private String title;
    private String description;
    private int duration;
    private String difficulty;
}
