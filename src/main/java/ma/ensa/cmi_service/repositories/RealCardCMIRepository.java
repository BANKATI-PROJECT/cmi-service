package ma.ensa.cmi_service.repositories;


import ma.ensa.cmi_service.entities.realcardcmi.RealCardCMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RealCardCMIRepository extends JpaRepository<RealCardCMI,Long> {
    // @Query("SELECT c FROM RealCardCMI c WHERE c.realClientCMI.saveToken = :saveToken AND c.num = :numCard")
    // Optional<RealCardCMI> findBySaveTokenAndNum(@Param("saveToken") String saveToken, @Param("numCard") String numCard);


    Optional<RealCardCMI> findByNum(String num);
}
