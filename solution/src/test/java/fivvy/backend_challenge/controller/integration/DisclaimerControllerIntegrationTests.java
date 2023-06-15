package fivvy.backend_challenge.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import fivvy.backend_challenge.BackendChallengeApplication;
import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.service.DisclaimerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Tests for the Disclaimer Controller
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DisclaimerControllerIntegrationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    private DisclaimerDTO getDisclaimerDTO(String name, String text, String version) {
        DisclaimerDTO disclaimerDTO = new DisclaimerDTO();
        disclaimerDTO.setName(name);
        disclaimerDTO.setText(text);
        disclaimerDTO.setVersion(version);
        return disclaimerDTO;
    }

    @Test
    public void createDisclaimerSuccesfullyTest() throws Exception {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO("John Doe", "a text", "1.0");
        mockMvc.perform(post("/disclaimer")
                        .content(objectMapper.writeValueAsString(disclaimerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.version").value("1.0"))
                .andExpect(jsonPath("$.text").value("a text"));
    }

    /**
     * Creates a disclaimer and then deletes it successfully
     */
    @Test
    public void createAndDeleteDisclaimerSuccesfullyTest() throws Exception {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO("John Doe", "a text", "1.0");
        MvcResult result = mockMvc.perform(post("/disclaimer")
                .content(objectMapper.writeValueAsString(disclaimerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Integer disclaimerId = JsonPath.read(result.getResponse().getContentAsString(),
                "$.id");

        mockMvc.perform(delete("/disclaimer/{id}", disclaimerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(disclaimerId));
    }

    /**
     * Tries to delete a disclaimer that does not exist and expects a 404
     */
    @Test
    public void deleteDisclaimerNotFoundTest() throws Exception {
        Long nonExistentDisclaimerId = 1L;
        mockMvc.perform(delete("/disclaimer/{id}", nonExistentDisclaimerId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Disclaimer not found"));
    }
}
