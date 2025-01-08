package ma.ensa.cmi_service.controllers;

import ma.ensa.cmi_service.services.RealClientCMIService;
import ma.ensa.cmi_service.entities.realcardcmi.RealCardCMI;
import ma.ensa.cmi_service.requests_responses.GetAllCardsRequest;
import ma.ensa.cmi_service.requests_responses.GetAllCardsResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CMIEndpoint {

    private static final String NAMESPACE_URI = "http://ensa.ma/cmi-service/requests_responses";

    @Autowired
    private RealClientCMIService realClientCMIService;
    
    public CMIEndpoint(RealClientCMIService realClientCMIService) {
        this.realClientCMIService = realClientCMIService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCardsRequest")
    @ResponsePayload
    public GetAllCardsResponse getAllCards(@RequestPayload GetAllCardsRequest request) {
        List<RealCardCMI> clientCards;
        GetAllCardsResponse response=null;
        try {
            clientCards = realClientCMIService.getCardsBySafeToken(request.getSaveToken());

            response = new GetAllCardsResponse();
            List<RealCardCMI> responseCards = response.getCards();
            Hibernate.initialize(clientCards);
            for (RealCardCMI card : clientCards) {
                RealCardCMI responseCard = new RealCardCMI();
                responseCard.setId(card.getId());
                responseCard.setNum(card.getNum());
                responseCard.setCvv(card.getCvv());
            responseCard.setExpire(card.getExpire());
            responseCard.setLabel(card.getLabel());
            responseCard.setSolde(card.getSolde());
            responseCards.add(responseCard);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    

    // @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCardRequest")
    // @ResponsePayload
    // public GetCardResponse getCard(@RequestPayload GetCardRequest request) {
    //     RealCardCMI card = realClientCMIService.getCardByNumber(request.getNumCard());

    //     GetCardResponse response = new GetCardResponse();
    //     RealCardCMI responseCard = new RealCardCMI();
    //     responseCard.setNum(card.getNum());
    //     responseCard.setCvv(card.getCvv());
    //     responseCard.setExpire(card.getExpire());
    //     responseCard.setLabel(card.getLabel());
    //     responseCard.setSolde(card.getSolde());
    //     response.setCard(responseCard);

    //     return response;
    // }
}
