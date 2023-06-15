package fivvy.backend_challenge.model;
import fivvy.backend_challenge.dto.DisclaimerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Entity
@Table(name = "disclaimer", schema = "public")
@NoArgsConstructor
@Getter
public class Disclaimer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;
    private String version;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    /**
     * Base constructor
     */
    public Disclaimer(DisclaimerDTO disclaimerDTO) {
        this.name = disclaimerDTO.getName();
        this.text = disclaimerDTO.getText();
        this.version = disclaimerDTO.getVersion();
    }
}
