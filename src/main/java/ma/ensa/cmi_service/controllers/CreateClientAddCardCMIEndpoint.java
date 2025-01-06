package ma.ensa.cmi_service.controllers;

import ma.ensa.cmi_service.services.RealClientCMIService;
import ma.ensa.cmi_service.entities.realcardcmi.RealCardCMI;
import ma.ensa.cmi_service.entities.realclientcmi.RealClientCMI;
import ma.ensa.cmi_service.repositories.RealCardCMIRepository;
import ma.ensa.cmi_service.requests_responses.AddRealCardRequest;
import ma.ensa.cmi_service.requests_responses.AddRealCardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class CreateClientAddCardCMIEndpoint {

    private static final String NAMESPACE_URI = "http://ensa.ma/cmi-service/requests_responses";

    @Autowired
    private RealClientCMIService realClientCMIService;
    @Autowired
    private RealCardCMIRepository realCardCMIRepository;
    
    public CreateClientAddCardCMIEndpoint(RealClientCMIService realClientCMIService) {
        this.realClientCMIService = realClientCMIService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddRealCardRequest")
    @ResponsePayload
    public AddRealCardResponse addRealCard(@RequestPayload AddRealCardRequest request) {
        // Service logic
        RealCardCMI card = new RealCardCMI();
        card.setNum(request.getCard().getNum());
        card.setCvv(request.getCard().getCvv());
        card.setExpire(request.getCard().getExpire());
        card.setLabel(request.getCard().getLabel());
        card.setSolde(1000 + (Math.random() * (5000 - 1000)));
        
        realCardCMIRepository.save(card);

        if (request.getSafeToken() == null || request.getSafeToken().isEmpty()) {
            // Générer un nouveau saveToken
            request.setSafeToken(UUID.randomUUID().toString());
        }

        RealClientCMI client = realClientCMIService.findOrCreateBySaveToken(request.getSafeToken());
        realClientCMIService.addCardToClient(client, card);

        AddRealCardResponse response = new AddRealCardResponse();
        response.setCardId(card.getId());
        response.setSafeToken(request.getSafeToken());
        return response;
    }

}
