package pe.com.agencymicroservice.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuideDto {
     private int agencyId;
    private String Name;
    private String Specilization;
    private String Experience;
    private String Certifications;
}
