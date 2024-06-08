package pe.com.agencymicroservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.agencymicroservice.Client.GuideClient.GuideClient;
import pe.com.agencymicroservice.Client.TripClient.TripClient;
import pe.com.agencymicroservice.Dto.GuideDto;
import pe.com.agencymicroservice.Dto.TripDto;
import pe.com.agencymicroservice.entities.Agency;
import pe.com.agencymicroservice.http.response.AgencyResponse;
import pe.com.agencymicroservice.repository.AgencyRepository;
import pe.com.agencymicroservice.service.AgencyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository _agencyRepository;

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
    public void updateAgency(Agency _agency) {
        _agencyRepository.save(_agency);
    }

    @Override
    public void deleteAgency(int _id) {
        _agencyRepository.deleteById(_id);
    }



    @Override
    public AgencyResponse getGuidesByAgencyId(int _agencyId){
        //System.out.println("Entrghdfgdfghdfghdfghdfgo???????????????dskjdgf");
        Agency agency = _agencyRepository.findById(_agencyId).orElse(new Agency());
        //System.out.println("Segundo");
        //System.out.println("agency: " + _guideClient.findAllGuideByAgencyId(_agencyId));
        List<GuideDto> guidesDto = _guideClient.findAllGuideByAgencyId(_agencyId);
        //System.out.println("tercero");
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
