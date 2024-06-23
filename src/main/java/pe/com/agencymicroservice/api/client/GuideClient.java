package pe.com.agencymicroservice.api.client;

import pe.com.agencymicroservice.mapping.dto.GuideDto;

import java.util.List;

public interface GuideClient {

    List<GuideDto> findAllGuideByAgencyId( int agencyId);
}
