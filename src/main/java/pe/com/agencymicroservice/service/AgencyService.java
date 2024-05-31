package pe.com.agencymicroservice.service;

import pe.com.agencymicroservice.entities.Agency;
import pe.com.agencymicroservice.http.response.GuideByAgencyResponse;

import java.util.List;

public interface AgencyService {
    public abstract Agency createAgency(Agency _agency);
    public abstract List<Agency> getAllAgency();
    public abstract void updateAgency(Agency _agency);
    public abstract void deleteAgency(int _id);
    public abstract Agency getAgencyById(int _id);

    //conexion
    GuideByAgencyResponse getGuidesByAgencyId(int _agencyId);

}
