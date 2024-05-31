package pe.com.agencymicroservice.Client.TripClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.com.agencymicroservice.Dto.GuideDto;
import pe.com.agencymicroservice.Dto.TripDto;

import java.util.List;

@Service
public class TripClientImpl implements TripClient{
    private final WebClient webClient;

    @Autowired
    public TripClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/api/v1").build();
        System.out.println("webClient: " + webClient);
    }

    @Override
    public List<TripDto> findAllTripByAgencyId(int agencyId) {
        try {

            System.out.println("try: " + agencyId);
            return webClient.get()
                    .uri("/trip/by-agency/{agencyId}", agencyId)
                    .retrieve()
                    .bodyToFlux(TripDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
