package ma.ensa.cmi_service.entities;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RealCardCMI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID unique pour chaque carte

    private String num; // Numéro de la carte
    private String cvv; // CVV de la carte
    private LocalDate expire; // Date d'expiration de la carte
    private String label; // Label de la carte
    private Double solde; // Solde de la carte

    @ManyToOne
    @JoinColumn(name = "real_client_cmi_id", nullable = false)
    private RealClientCMI realClientCMI; // Client associé à cette carte

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expire) {
        this.expire = expire;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public RealClientCMI getRealClientCMI() {
        return realClientCMI;
    }

    public void setRealClientCMI(RealClientCMI realClientCMI) {
        this.realClientCMI = realClientCMI;
    }
}
