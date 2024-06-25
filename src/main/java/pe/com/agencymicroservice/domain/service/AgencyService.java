package pe.com.agencymicroservice.domain.service;

import pe.com.agencymicroservice.domain.model.Agency;
import pe.com.agencymicroservice.resource.AgencyResponse;

import java.util.List;
import pe.com.agencymicroservice.mapping.dto.LoginDto;
import pe.com.agencymicroservice.mapping.dto.LoginResponseDto;

public interface AgencyService {
    public abstract Agency createAgency(Agency _agency);
    public abstract List<AgencyResponse> getAllAgency();
    public abstract void updateAgency(Agency _agency);
    public abstract void deleteAgency(int _id);
    public abstract LoginResponseDto authenticate(LoginDto loginDto);


    //conexion
    AgencyResponse getGuidesByAgencyId(int _agencyId);

}
