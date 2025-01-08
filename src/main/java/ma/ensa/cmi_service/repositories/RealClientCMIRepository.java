package ma.ensa.cmi_service.repositories;


import ma.ensa.cmi_service.entities.realclientcmi.RealClientCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealClientCMIRepository extends JpaRepository<RealClientCMI,String> {
    Optional<RealClientCMI> findBySaveToken(String saveToken);
}
