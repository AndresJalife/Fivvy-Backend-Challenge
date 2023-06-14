package fivvy.backend_challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fivvy.backend_challenge.model.Disclaimer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for a Disclaimer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisclaimerDTO {
    private Long id;
    private String name;
    private String text;
    private String version;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;

    /**
     * Basic constructor
     */
    public DisclaimerDTO(String name, String text, String version) {
        this.name = name;
        this.text = text;
        this.version = version;
    }

    /**
     * Constructor from a Disclaimer
     * @param disclaimer Model Disclaimer
     */
    public DisclaimerDTO(Disclaimer disclaimer) {
        this.id = disclaimer.getId();
        this.name = disclaimer.getName();
        this.text = disclaimer.getText();
        this.version = disclaimer.getVersion();
        this.createdAt = disclaimer.getCreatedAt();
        this.updatedAt = disclaimer.getUpdatedAt();
    }
}
