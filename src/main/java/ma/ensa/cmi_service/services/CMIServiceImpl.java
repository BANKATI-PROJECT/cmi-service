package ma.ensa.cmi_service.services;

import ma.ensa.cmi_service.repositories.RealCardCMIRepository;
import ma.ensa.cmi_service.repositories.RealClientCMIRepository;
import ma.ensa.cmi_service.entities.Client;
import ma.ensa.cmi_service.entities.RealCardCMI;
import ma.ensa.cmi_service.entities.RealClientCMI;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@WebService
@Service
public class CMIServiceImpl implements CMIService {

    @Autowired
    private  RealClientCMIRepository realClientCMIRepository;
    @Autowired
    private  RealCardCMIRepository realCardCMIRepository;




    public CMIServiceImpl(){

    }

    @Transactional
    @Override
    public ResponseEntity<Client> addCard(Client client, String cardNumber, String cvv, LocalDate expiryDate, String label) {
        RealClientCMI realClientCMI;
        RealCardCMI newCard = new RealCardCMI(cardNumber, cvv, expiryDate, label);

        // Vérification si le saveToken du client est null
        if (client.getSaveToken() == null) {
            String newSaveToken = generateSaveToken(); // Générer un nouveau saveToken
            realClientCMI = new RealClientCMI(newSaveToken);
            client.setSaveToken(newSaveToken); // Associer le saveToken au client
            associateCardWithClient(realClientCMI, newCard); // Associer la carte au client
            realClientCMIRepository.save(realClientCMI); // Sauvegarder le client avec la carte
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } else {
            // Rechercher le client existant par saveToken
            Optional<RealClientCMI> clientOptional = realClientCMIRepository.findBySaveToken(client.getSaveToken());

            if (clientOptional.isPresent()) {
                realClientCMI = clientOptional.get();
                associateCardWithClient(realClientCMI, newCard); // Ajouter la carte au client existant
                realClientCMIRepository.save(realClientCMI); // Sauvegarder le client avec la nouvelle carte
                return ResponseEntity.ok(client);
            } else {
                // Si le client n'est pas trouvé, retourner une réponse d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }

    /**
     * Associe une carte à un client et persiste la carte.
     */
    private void associateCardWithClient(RealClientCMI client, RealCardCMI card) {
        card.setRealClientCMI(client);
        client.getRealCardsCMI().add(card);
        realCardCMIRepository.save(card); // Persister la carte
    }

    /**
     * Génère un saveToken unique.
     */
    private String generateSaveToken() {
        return UUID.randomUUID().toString();
    }


    @Transactional
    @Override
    public ResponseEntity<String> rechargeWallet(String saveToken, String cardNumber, double amount) {
        // Vérifier si le saveToken est valide
        Optional<RealClientCMI> clientOptional = realClientCMIRepository.findBySaveToken(saveToken);

        if (clientOptional.isEmpty()) {
            // Client introuvable
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found for the provided saveToken.");
        }

        RealClientCMI client = clientOptional.get();

        // Trouver la carte spécifique avec le numéro de carte fourni
        Optional<RealCardCMI> cardOptional = client.getRealCardsCMI()
                .stream()
                .filter(card -> card.getNum().equals(cardNumber))
                .findFirst();

        if (cardOptional.isEmpty()) {
            // Carte introuvable pour ce client
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found for the provided card number.");
        }

        RealCardCMI card = cardOptional.get();

        // Vérifier si la carte a un solde suffisant
        if (card.getSolde() < amount) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance in the specified card.");
        }

        // Effectuer la transaction, en déduisant le montant du solde de la carte
        card.setSolde(card.getSolde()- amount);
        realCardCMIRepository.save(card);

        return ResponseEntity.ok("Wallet successfully recharged using the specified card.");
    }

}
