package pe.com.agencymicroservice.Client;

import org.springframework.web.bind.annotation.GetMapping;

import pe.com.agencymicroservice.Dto.GuideDto;

import java.util.List;

public interface GuideClient {

    List<GuideDto> findAllGuideByAgencyId( int agencyId);
}
