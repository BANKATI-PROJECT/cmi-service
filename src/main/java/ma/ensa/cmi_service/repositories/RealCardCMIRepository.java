package ma.ensa.cmi_service.repositories;

<<<<<<< HEAD
import ma.ensa.cmi_service.entities.RealCardCMI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealCardCMIRepository extends JpaRepository<RealCardCMI,Long> {
=======

import ma.ensa.cmi_service.entities.RealCardCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealCardCMIRepository extends JpaRepository<RealCardCMI, Long> {
    Optional<RealCardCMI> findByNum(String num);
>>>>>>> e45ccbe784073567c68ce915fd3bc524335bf5aa
}
