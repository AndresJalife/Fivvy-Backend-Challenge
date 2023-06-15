package fivvy.backend_challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fivvy.backend_challenge.model.Disclaimer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for a Disclaimer
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class DisclaimerDTO {

    private Long id;

    @Setter
    private String name;

    @Setter
    private String text;

    @Setter
    private String version;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

    /**
     * Constructor from a Disclaimer
     *
     * @param disclaimer Model Disclaimer
     */
    public DisclaimerDTO(Disclaimer disclaimer) {
        this.id = disclaimer.getId();
        this.name = disclaimer.getName();
        this.text = disclaimer.getText();
        this.version = disclaimer.getVersion();
        this.createdAt = disclaimer.getCreatedAt().getTime();
        this.updatedAt = disclaimer.getUpdatedAt().getTime();
    }
}
