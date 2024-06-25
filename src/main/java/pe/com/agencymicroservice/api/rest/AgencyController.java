package pe.com.agencymicroservice.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.agencymicroservice.domain.model.Agency;
import pe.com.agencymicroservice.mapping.dto.LoginDto;
import pe.com.agencymicroservice.mapping.dto.LoginResponseDto;
import pe.com.agencymicroservice.resource.AgencyResponse;
import pe.com.agencymicroservice.domain.service.AgencyService;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/agency")
public class AgencyController {
    @Autowired
    private AgencyService _agencyService;

    public AgencyController(AgencyService agencyService) {
        this._agencyService = agencyService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
        try {
            LoginResponseDto response = _agencyService.authenticate(loginDto);
            if (response.isSuccess()) {
                Map<String, Object> result = new HashMap<>();
                result.put("agency", response.getAgency()); // Incluye toda la información de la agencia
                result.put("token", response.getToken());
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<Agency> createAgency(@RequestBody Agency _agency){
        Agency createdAgency = _agencyService.createAgency(_agency);
        return new ResponseEntity<>(createdAgency, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgencyResponse>> getAllAgency(){
        List<AgencyResponse> guides = _agencyService.getAllAgency();
        return new ResponseEntity<>(guides, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAgency(@PathVariable("id") int id, @RequestBody Agency _agency){
        _agency.setId(id);
        _agencyService.updateAgency(_agency);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable("id") int id){
        _agencyService.deleteAgency(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<AgencyResponse> getByAgencyId(@PathVariable int agencyId) {
        return ResponseEntity.ok(_agencyService.getGuidesByAgencyId(agencyId));
    }
}
