package ma.ensa.cmi_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ma.ensa.cmi_service.entities.realcardcmi.RealCardCMI;
import ma.ensa.cmi_service.entities.realclientcmi.RealClientCMI;
import ma.ensa.cmi_service.requests_responses.MessageResponse;
import ma.ensa.cmi_service.requests_responses.TransactionRequest;
import ma.ensa.cmi_service.services.RealClientCMIService;

@Endpoint
public class TransactionEndpoint {

    private static final String NAMESPACE_URI = "http://ensa.ma/cmi-service/requests_responses";

    @Autowired
    private RealClientCMIService realClientCMIService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "TransactionRequest")
    @ResponsePayload
    public MessageResponse handleTransaction(@RequestPayload TransactionRequest request) {
        MessageResponse response = new MessageResponse();

        try {
            // Retrieve client using the saveToken
            RealClientCMI client = realClientCMIService.findBySaveToken(request.getSaveToken());
            if (client == null) {
                throw new IllegalArgumentException("Invalid saveToken");
            }

            // Perform operations with the ID and amount
            Long id = request.getId();
            double amount = request.getAmount();

            // Example logic: Deduct the amount from a specific card
            RealCardCMI card = client.getRealCardsCMI().stream()
                    .filter(c -> c.getId()==id)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Card with given ID not found"));
            System.out.println(card.getSolde()+"   "+amount);
            if (card.getSolde() < amount) {
                throw new IllegalArgumentException("Insufficient balance");
            }

            card.setSolde(card.getSolde() - amount);
            realClientCMIService.updateCard(card);

            // Set response message
            response.setMessage("Transaction successful");
        } catch (Exception e) {
            response.setMessage("Transaction failed: " + e.getMessage());
        }

        return response;
    }
}
