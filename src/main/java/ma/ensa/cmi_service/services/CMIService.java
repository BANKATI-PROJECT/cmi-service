package ma.ensa.cmi_service.services;



import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import ma.ensa.cmi_service.entities.Client;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;


@WebService
public interface CMIService {

    @WebMethod
    public ResponseEntity<Client> addCard(Client client , String cardNumber, String cvv, LocalDate expiryDate, String label);

    @WebMethod
    public ResponseEntity<String> rechargeWallet(String saveToken, String cardNumber, double amount) ;

    }

