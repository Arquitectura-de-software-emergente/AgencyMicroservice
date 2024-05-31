package pe.com.agencymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.agencymicroservice.entities.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
}
