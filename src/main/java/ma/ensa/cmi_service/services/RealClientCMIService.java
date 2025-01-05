package ma.ensa.cmi_service.services;

import ma.ensa.cmi_service.entities.RealCardCMI;
import ma.ensa.cmi_service.entities.RealClientCMI;
import ma.ensa.cmi_service.repositories.RealClientCMIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RealClientCMIService {

    @Autowired
    private RealClientCMIRepository realClientCMIRepository;

    public RealClientCMI findOrCreateBySaveToken(String saveToken) {
        return realClientCMIRepository.findBySaveToken(saveToken)
                .orElseGet(() -> {
                    RealClientCMI newClient = new RealClientCMI();
                    newClient.setSaveToken(saveToken);
                    return realClientCMIRepository.save(newClient);
                });
    }

    public void addCardToClient(RealClientCMI client, RealCardCMI card) {
        if (client.getRealCardsCMI() == null) {
            client.setRealCardsCMI(new ArrayList<>());
        }
        client.getRealCardsCMI().add(card);
        realClientCMIRepository.save(client);
    }
}
