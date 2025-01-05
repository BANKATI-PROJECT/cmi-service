package ma.ensa.cmi_service.repositories;


import ma.ensa.cmi_service.entities.RealClientCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealClientCMIRepository extends JpaRepository<RealClientCMI, Long> {
    Optional<RealClientCMI> findBySaveToken(String saveToken);
}
