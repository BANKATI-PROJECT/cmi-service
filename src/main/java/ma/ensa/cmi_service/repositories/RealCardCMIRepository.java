package ma.ensa.cmi_service.repositories;


import ma.ensa.cmi_service.entities.RealCardCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealCardCMIRepository extends JpaRepository<RealCardCMI, Long> {
    Optional<RealCardCMI> findByNum(String num);
}
