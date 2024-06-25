package pe.com.agencymicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.agencymicroservice.api.client.GuideClient;
import pe.com.agencymicroservice.api.client.TripClient;
import pe.com.agencymicroservice.config.JwtUtil;
import pe.com.agencymicroservice.mapping.dto.GuideDto;
import pe.com.agencymicroservice.mapping.dto.LoginDto;
import pe.com.agencymicroservice.mapping.dto.LoginResponseDto;
import pe.com.agencymicroservice.mapping.dto.TripDto;
import pe.com.agencymicroservice.domain.model.Agency;
import pe.com.agencymicroservice.resource.AgencyResponse;
import pe.com.agencymicroservice.domain.persistence.AgencyRepository;
import pe.com.agencymicroservice.domain.service.AgencyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository _agencyRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private GuideClient _guideClient;

    @Autowired
    private TripClient _tripClient;

    @Override
    public Agency createAgency(Agency _agency) {
        return _agencyRepository.save(_agency);
    }

    @Override
    public List<AgencyResponse> getAllAgency() {
        List<Agency> agencies = getAll();
        List<AgencyResponse> responses = new ArrayList<>();

        for (Agency agency : agencies) {
            List<GuideDto> guidesDto = _guideClient.findAllGuideByAgencyId(agency.getId());
            List<TripDto> tripsDto = _tripClient.findAllTripByAgencyId(agency.getId());
            AgencyResponse response = AgencyResponse.builder()
                    .Id(agency.getId())
                    .Name(agency.getName())
                    .Description(agency.getDescription())
                    .PhoneNumber(agency.getPhoneNumber())
                    .Email(agency.getEmail())
                    .Address(agency.getAddress())
                    .guidesList(guidesDto)
                    .tripsList(tripsDto)
                    .build();
            responses.add(response);
        }

        return responses;
    }

    public List<Agency> getAll() {
        return (List<Agency>) _agencyRepository.findAll();
    }

    @Override
    public LoginResponseDto authenticate(LoginDto loginDto) {
        Agency agency = _agencyRepository.findByEmail(loginDto.getEmail())
                .filter(a -> a.getPassword().equals(loginDto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(loginDto.getEmail());
        return new LoginResponseDto(agency.getEmail(), token, agency, true);
    }

    @Override
    public void updateAgency(Agency _agency) {
        _agencyRepository.save(_agency);
    }

    @Override
    public void deleteAgency(int _id) {
        _agencyRepository.deleteById(_id);
    }

    @Override
    public AgencyResponse getGuidesByAgencyId(int _agencyId){
        Agency agency = _agencyRepository.findById(_agencyId).orElse(new Agency());
        List<GuideDto> guidesDto = _guideClient.findAllGuideByAgencyId(_agencyId);
        List<TripDto> tripsDto = _tripClient.findAllTripByAgencyId(_agencyId);
        return AgencyResponse.builder()
                .Id(agency.getId())
                .Name(agency.getName())
                .Description(agency.getDescription())
                .PhoneNumber(agency.getPhoneNumber())
                .Email(agency.getEmail())
                .Address(agency.getAddress())
                .guidesList(guidesDto)
                .tripsList(tripsDto)
                .build();
    }
}
