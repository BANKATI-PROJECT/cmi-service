package ma.ensa.cmi_service.services;

import ma.ensa.cmi_service.entities.realcardcmi.RealCardCMI;
import ma.ensa.cmi_service.entities.realclientcmi.RealClientCMI;
import ma.ensa.cmi_service.repositories.RealCardCMIRepository;
import ma.ensa.cmi_service.repositories.RealClientCMIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RealClientCMIService {

    @Autowired
    private RealClientCMIRepository realClientCMIRepository;
    @Autowired
    private RealCardCMIRepository realCardCMIRepository;

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
            client.setRealCardsCMI(new ArrayList<RealCardCMI>());
        }
        client.getRealCardsCMI().add(card);
        realClientCMIRepository.save(client);
    }

    public RealCardCMI getCardByNumber(String numCard) {
        return realCardCMIRepository.findByNum(numCard)
                .orElseThrow(() -> new RuntimeException("Carte introuvable"));
    }

    public void updateCard(RealCardCMI card) {
        RealCardCMI existingCard = realCardCMIRepository.findByNum(card.getNum())
                .orElseThrow(() -> new RuntimeException("Carte introuvable"));

        existingCard.setCvv(card.getCvv());
        existingCard.setExpire(card.getExpire());
        existingCard.setLabel(card.getLabel());
        existingCard.setSolde(card.getSolde());
    }

    public List<RealCardCMI> getCardsBySafeToken(String safeToken) throws Exception {
        // Fetch cards from the database or repository based on the safeToken
        RealClientCMI client = realClientCMIRepository.findById(safeToken).orElse(null);
        if (client == null) {
            throw new Exception("Client with the given safeToken not found.");
        }
        return client.getRealCardsCMI(); // Assuming RealClientCMI has a getCards() method
    }
    
    

}
