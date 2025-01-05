package ma.ensa.cmi_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identifiant unique du client

    @Column(nullable = false)
    private String nom;  // Nom du client

    @Column(nullable = false)
    private String prenom;  // Prénom du client

    @Column(nullable = false, unique = true)
    private String email;  // Email du client

    @Column(nullable = false)
    private String telephone;  // Numéro de téléphone du client

    @Column(nullable = false, unique = true)
    private String saveToken;  // Token unique pour le client







}
