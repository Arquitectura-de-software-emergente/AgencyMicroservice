package pe.com.agencymicroservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.agencymicroservice.Client.GuideClient;
import pe.com.agencymicroservice.Dto.GuideDto;
import pe.com.agencymicroservice.entities.Agency;
import pe.com.agencymicroservice.http.response.GuideByAgencyResponse;
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

    @Override
    public Agency createAgency(Agency _agency) {
        return _agencyRepository.save(_agency);
    }


    @Override
    public List<GuideByAgencyResponse> getAllAgency() {
        List<Agency> agencies = getAll();
        List<GuideByAgencyResponse> responses = new ArrayList<>();

        for (Agency agency : agencies) {
            List<GuideDto> guidesDto = _guideClient.findAllGuideByAgencyId(agency.getId());
            GuideByAgencyResponse response = GuideByAgencyResponse.builder()
                    .Id(agency.getId())
                    .Name(agency.getName())
                    .Description(agency.getDescription())
                    .PhoneNumber(agency.getPhoneNumber())
                    .Email(agency.getEmail())
                    .Address(agency.getAddress())
                    .guidesDtoList(guidesDto)
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
    public GuideByAgencyResponse getGuidesByAgencyId(int _agencyId){
        System.out.println("Entrghdfgdfghdfghdfghdfgo???????????????dskjdgf");
        Agency agency = _agencyRepository.findById(_agencyId).orElse(new Agency());
        System.out.println("Segundo");
        System.out.println("agency: " + _guideClient.findAllGuideByAgencyId(_agencyId));
        List<GuideDto> guidesDto = _guideClient.findAllGuideByAgencyId(_agencyId);
        System.out.println("tercero");



        return GuideByAgencyResponse.builder()
                .Id(agency.getId())
                .Name(agency.getName())
                .Description(agency.getDescription())
                .PhoneNumber(agency.getPhoneNumber())
                .Email(agency.getEmail())
                .Address(agency.getAddress())
                .guidesDtoList(guidesDto)
                .build();

    }

}
