package fivvy.backend_challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fivvy.backend_challenge.model.Acceptance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for an Acceptance
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class AcceptanceDTO {
    @Setter
    @JsonProperty("disclaimer_id")
    private Long disclaimerId;

    @Setter
    @JsonProperty("user_id")
    private Long userId;

    @Setter
    @JsonProperty("create_at")
    private Long createdAt;

    /**
     * Constructor from an Acceptance
     *
     * @param acceptance Model Acceptance
     */
    public AcceptanceDTO(Acceptance acceptance) {
        this.disclaimerId = acceptance.getDisclaimerId();
        this.userId = acceptance.getUserId();
        this.createdAt = acceptance.getCreatedAt().getTime();
    }
}
