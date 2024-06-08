package pe.com.agencymicroservice.Client.GuideClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.com.agencymicroservice.Client.GuideClient.GuideClient;
import pe.com.agencymicroservice.Dto.GuideDto;

import java.util.List;

@Service
public class GuideClientImpl implements GuideClient {

    private final WebClient webClient;

    @Autowired
    public GuideClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1/guide").build();
            System.out.println("webClient: " + webClient);
    }

    @Override
    public List<GuideDto> findAllGuideByAgencyId(int agencyId) {
        System.out.println("findAllGuideByAgencyId: " + agencyId);
        try {

            System.out.println("try: " + agencyId);
            return webClient.get()
                    .uri("/by-agency/{agencyId}", agencyId)
                    .retrieve()
                    .bodyToFlux(GuideDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}