package fivvy.backend_challenge.model;

import fivvy.backend_challenge.dto.AcceptanceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "acceptance", schema = "public")
@NoArgsConstructor
@Getter
@IdClass(AcceptanceIds.class)
public class Acceptance {
    @Id
    @Column(name = "disclaimer_id")
    private Long disclaimerId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_at")
    private Timestamp createdAt;

    /**
     * Constructor from a AcceptanceDTO
     */
    public Acceptance(AcceptanceDTO acceptanceDTO) {
        this.disclaimerId = acceptanceDTO.getDisclaimerId();
        this.userId = acceptanceDTO.getUserId();
    }

    /**
     * Sets the creation timestamp when its created
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
