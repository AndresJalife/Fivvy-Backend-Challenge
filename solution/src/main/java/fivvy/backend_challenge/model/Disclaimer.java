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

    /**
     * Base constructor
     */
    public Disclaimer(DisclaimerDTO disclaimerDTO) {
        this.name = disclaimerDTO.getName();
        this.text = disclaimerDTO.getText();
        this.version = disclaimerDTO.getVersion();
    }

    /**
     * Sets the creation and update timestamps when its created
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    /**
     * Updates the disclaimer
     *
     * @param disclaimerDTO the disclaimer DTO to update
     */
    public void update(DisclaimerDTO disclaimerDTO) {
        this.name = disclaimerDTO.getName();
        this.text = disclaimerDTO.getText();
        this.version = disclaimerDTO.getVersion();
    }
}
