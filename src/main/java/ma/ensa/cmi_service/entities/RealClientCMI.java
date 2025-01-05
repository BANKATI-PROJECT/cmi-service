package ma.ensa.cmi_service.entities;

import jakarta.persistence.*;
import java.util.List;

public class RealClientCMI {

    @Id
    private String saveToken;

    @OneToMany(mappedBy = "realClientCMI", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RealCardCMI> realCardsCMI; // Liste des cartes associées à ce client


    public String getSaveToken() {
        return saveToken;
    }

    public void setSaveToken(String saveToken) {
        this.saveToken = saveToken;
    }

    public List<RealCardCMI> getRealCardsCMI() {
        return realCardsCMI;
    }

    public void setRealCardsCMI(List<RealCardCMI> realCardsCMI) {
        this.realCardsCMI = realCardsCMI;
    }
}