package pe.com.agencymicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.agencymicroservice.entities.Agency;
import pe.com.agencymicroservice.http.response.GuideByAgencyResponse;
import pe.com.agencymicroservice.service.AgencyService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AgencyController {
    @Autowired
    private AgencyService _agencyService;

    public AgencyController(AgencyService agencyService) {
        this._agencyService = agencyService;


    }
    @PostMapping("/agency")
    public ResponseEntity<Agency> createAgency(@RequestBody Agency _agency){
        Agency createdAgency = _agencyService.createAgency(_agency);
        return new ResponseEntity<>(createdAgency, HttpStatus.CREATED);
    }

    @GetMapping("/agency")
    public ResponseEntity<List<Agency>> getAllAgency(){
        List<Agency> guides = _agencyService.getAllAgency();
        return new ResponseEntity<>(guides, HttpStatus.OK);
    }

    @PutMapping("/agency/{id}")
    public ResponseEntity<Void> updateAgency(@PathVariable("id") int id, @RequestBody Agency _agency){
        _agency.setId(id);
        _agencyService.updateAgency(_agency);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/agency/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable("id") int id){
        _agencyService.deleteAgency(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/agency/{id}")
    public ResponseEntity<Agency> getAgencyById(@PathVariable("id") int id){
        try {
            Agency guide = _agencyService.getAgencyById(id);
            return new ResponseEntity<>(guide, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/agency-guide/{agencyId}")
    public ResponseEntity<GuideByAgencyResponse> getGuidesByAgencyId(@PathVariable int agencyId) {
        return ResponseEntity.ok(_agencyService.getGuidesByAgencyId(agencyId));
    }

}
