package ma.ensa.cmi_service.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class RealCardCMI {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String num;
    private String cvv;
    private LocalDate expire;
    private String label;

    @ManyToOne
    @JoinColumn(name = "real_client_cmi_id")
    private RealClientCMI realClientCMI;

    private Double solde;

    public RealCardCMI(String num, String cvv, LocalDate expire, String label) {
        this.num = num;
        this.cvv = cvv;
        this.expire = expire;
        this.label = label;
        this.solde = Double.valueOf("10000");
    }





}

