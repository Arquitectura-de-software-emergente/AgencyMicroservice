package pe.com.agencymicroservice.api.client;

import pe.com.agencymicroservice.mapping.dto.TripDto;

import java.util.List;

public interface TripClient {
    List<TripDto> findAllTripByAgencyId(int agencyId);
}
