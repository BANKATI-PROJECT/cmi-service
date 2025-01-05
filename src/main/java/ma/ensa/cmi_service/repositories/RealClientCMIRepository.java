package ma.ensa.cmi_service.repositories;


import ma.ensa.cmi_service.entities.RealClientCMI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealClientCMIRepository extends JpaRepository<RealClientCMI,String> {
    Optional<RealClientCMI> findBySaveToken(String saveToken);
}
