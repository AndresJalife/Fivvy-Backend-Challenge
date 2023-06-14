package fivvy.backend_challenge.model;


import fivvy.backend_challenge.dto.DisclaimerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disclaimer", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disclaimer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;
    private String version;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * Base constructor
     */
    public Disclaimer(DisclaimerDTO disclaimerDTO) {
        this.name = disclaimerDTO.getName();
        this.text = disclaimerDTO.getText();
        this.version = disclaimerDTO.getVersion();
    }
}
