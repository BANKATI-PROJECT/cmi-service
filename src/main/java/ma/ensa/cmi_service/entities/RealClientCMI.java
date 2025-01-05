package ma.ensa.cmi_service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

 @Getter
 @Setter
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Entity
public class RealClientCMI {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String saveToken;

    public RealClientCMI(Long id, String saveToken) {
        this.id = id;
        this.saveToken = saveToken;
    }
    @OneToMany(mappedBy = "realClientCMI", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RealCardCMI> realCardsCMI = new ArrayList<>();


     public RealClientCMI(String saveToken) {
         this.saveToken=saveToken;
     }


 }
