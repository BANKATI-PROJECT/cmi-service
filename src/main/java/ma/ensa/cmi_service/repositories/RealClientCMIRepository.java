package ma.ensa.cmi_service.repositories;

<<<<<<< HEAD
import ma.ensa.cmi_service.entities.RealClientCMI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealClientCMIRepository extends JpaRepository<RealClientCMI,String> {
=======

import ma.ensa.cmi_service.entities.RealClientCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealClientCMIRepository extends JpaRepository<RealClientCMI, Long> {
    Optional<RealClientCMI> findBySaveToken(String saveToken);
>>>>>>> e45ccbe784073567c68ce915fd3bc524335bf5aa
}
