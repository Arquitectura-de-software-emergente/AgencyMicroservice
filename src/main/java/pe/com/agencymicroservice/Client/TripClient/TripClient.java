package pe.com.agencymicroservice.Client.TripClient;

import pe.com.agencymicroservice.Dto.GuideDto;
import pe.com.agencymicroservice.Dto.TripDto;

import java.util.List;

public interface TripClient {
    List<TripDto> findAllTripByAgencyId(int agencyId);
}
