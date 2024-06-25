package pe.com.agencymicroservice.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.agencymicroservice.domain.model.Agency;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String email;
    private String token;
    private Agency agency;
    private boolean success;

    public LoginResponseDto(String email, String token, Agency agency, boolean success) {
        this.email = email;
        this.token = token;
        this.agency = agency;
        this.success = success;
    }

}
