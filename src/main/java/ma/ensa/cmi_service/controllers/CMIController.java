package ma.ensa.cmi_service.controllers;

import ma.ensa.cmi_service.dtos.ClientDTO;
import ma.ensa.cmi_service.entities.RealCardCMI;
import ma.ensa.cmi_service.entities.RealClientCMI;
import ma.ensa.cmi_service.feign.AccountManagementFeignClient;
import ma.ensa.cmi_service.services.RealClientCMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cmi-service")
public class CMIController {

    @Autowired
    private RealClientCMIService realClientCMIService;

    @Autowired
    private AccountManagementFeignClient accountFeignClient;

    @PostMapping("/clients/cards/{id}")
    public String addRealCard(@PathVariable Long id, @RequestBody RealCardCMI cardRequest
    ) {

        ClientDTO clientDTO = accountFeignClient.getClientById(id);

        if (clientDTO == null) {
            return "Client introuvable";
        }

        String saveToken = clientDTO.getSaveToken();
        if (saveToken == null || saveToken.isEmpty()) {
            // Générer un nouveau saveToken
            saveToken = UUID.randomUUID().toString();
            accountFeignClient.updateClientSaveTokenById(id, saveToken);
        }

        RealClientCMI realClient = realClientCMIService.findOrCreateBySaveToken(saveToken);
        cardRequest.setSolde(1000 + (Math.random() * (5000 - 1000)));
        cardRequest.setRealClientCMI(realClient);
        realClientCMIService.addCardToClient(realClient, cardRequest);
        return "Carte ajoutée avec succès";
    }
}
