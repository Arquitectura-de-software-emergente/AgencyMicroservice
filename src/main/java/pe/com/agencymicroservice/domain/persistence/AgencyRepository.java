package pe.com.agencymicroservice.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.agencymicroservice.domain.model.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}
