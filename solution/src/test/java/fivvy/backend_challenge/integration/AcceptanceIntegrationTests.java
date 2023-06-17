package fivvy.backend_challenge.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import fivvy.backend_challenge.dto.AcceptanceDTO;
import fivvy.backend_challenge.dto.DisclaimerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Tests for the Acceptance Entity
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AcceptanceIntegrationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    /**
     * Tests the creation of an acceptance
     */
    @Test
    public void createAcceptance() throws Exception {
        DisclaimerDTO disclaimerDTO = new DisclaimerDTO();
        disclaimerDTO.setName("Test Disclaimer");
        disclaimerDTO.setText("a text");

        MvcResult mvcResult = mockMvc.perform(post("/disclaimer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(disclaimerDTO)))
                    .andExpect(status().isCreated())
                    .andReturn();

        Integer disclaimerId = JsonPath.read(mvcResult.getResponse().getContentAsString(),
                "$.id");
        AcceptanceDTO acceptanceDTO = new AcceptanceDTO();
        acceptanceDTO.setDisclaimerId(disclaimerId.longValue());
        acceptanceDTO.setUserId(1L);

        mockMvc.perform(post("/acceptance")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(acceptanceDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.disclaimer_id").value(disclaimerId))
                    .andExpect(jsonPath("$.user_id").value(1L))
                    .andExpect(jsonPath("$.create_at").exists());
    }

    /**
     * Tests the creation of an acceptance with a non existing disclaimer
     */
    @Test
    public void createAcceptanceWithNonExistingDisclaimer() throws Exception {
        AcceptanceDTO acceptanceDTO = new AcceptanceDTO();
        acceptanceDTO.setDisclaimerId(1L);
        acceptanceDTO.setUserId(1L);

        mockMvc.perform(post("/acceptance")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(acceptanceDTO)))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.error").value("Disclaimer not found"));
    }
}
