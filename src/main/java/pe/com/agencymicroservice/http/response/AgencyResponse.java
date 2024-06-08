package pe.com.agencymicroservice.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.agencymicroservice.Dto.GuideDto;
import pe.com.agencymicroservice.Dto.TripDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgencyResponse {
    private int Id;
    private String Name;
    private String Description;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private List<GuideDto> guidesList;
    private List<TripDto> tripsList;
}
