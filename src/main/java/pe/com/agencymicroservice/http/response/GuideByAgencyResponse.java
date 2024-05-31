package pe.com.agencymicroservice.http.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.agencymicroservice.Dto.GuideDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuideByAgencyResponse {
    private int Id;
    private String Name;
    private String Description;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private List<GuideDto> guidesDtoList;
}
