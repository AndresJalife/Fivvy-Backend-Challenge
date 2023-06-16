package fivvy.backend_challenge.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
 * Integration Tests for the Disclaimer Entity
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DisclaimerIntegrationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

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
        MvcResult result = creatBasiceDisclaimer();
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

    /**
     * Creates and Updates a disclaimer successfully
     */
    @Test
    public void createAndUpdateDisclaimerSuccesfullyTest() throws Exception {
        MvcResult result = creatBasiceDisclaimer();
        Integer disclaimerId = JsonPath.read(result.getResponse().getContentAsString(),
                "$.id");
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO(disclaimerId.longValue(), "Jane " +
                        "Doe",
                "another text", "2.0");
        mockMvc.perform(patch("/disclaimer")
                .content(objectMapper.writeValueAsString(disclaimerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(disclaimerId))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.version").value("2.0"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andExpect(jsonPath("$.text").value("another text"));
    }

    /**
     * Tries to update a disclaimer that does not exist and expects a 404
     */
    @Test
    public void updateDisclaimerNotFoundTest() throws Exception {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO(1L, "John Doe", "a text", "1.0");
        mockMvc.perform(patch("/disclaimer")
                .content(objectMapper.writeValueAsString(disclaimerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Disclaimer not found"));
    }

    /**
     * Tries to update a disclaimer without the id and expects a 404
     */
    @Test
    public void updateDisclaimerWithIdMissingTest() throws Exception {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO(null, "John Doe", "a text", "1.0");
        mockMvc.perform(patch("/disclaimer")
                .content(objectMapper.writeValueAsString(disclaimerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Missing Id"));
    }

    /**
     * Creates a disclaimer and then gets it successfully
     */
    @Test
    public void createAndGetDisclaimerSuccesfullyTest() throws Exception {
        MvcResult result = creatBasiceDisclaimer();
        Integer disclaimerId = JsonPath.read(result.getResponse().getContentAsString(),
                "$.id");

        mockMvc.perform(get("/disclaimer/{id}", disclaimerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(disclaimerId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.version").value("1.0"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andExpect(jsonPath("$.text").value("a text"));
    }

    /**
     * Tries to get a disclaimer that does not exist and expects a 404
     */
    @Test
    public void getDisclaimerNotFoundTest() throws Exception {
        Long nonExistentDisclaimerId = 1L;
        mockMvc.perform(get("/disclaimer/{id}", nonExistentDisclaimerId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Disclaimer not found"));
    }

    /**
     * Creates two disclaimers and then gets them all successfully
     */
    @Test
    public void createAndGetAllDisclaimersSuccesfullyTest() throws Exception {
        creatBasiceDisclaimer();
        createDisclaimer("Jane Doe", "a text", "1.0");

        mockMvc.perform(get("/disclaimer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].name").value("John Doe"))
                .andExpect(jsonPath("$.[0].version").value("1.0"))
                .andExpect(jsonPath("$.[0].created_at").exists())
                .andExpect(jsonPath("$.[0].updated_at").exists())
                .andExpect(jsonPath("$.[0].text").value("a text"))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$.[1].version").value("1.0"))
                .andExpect(jsonPath("$.[1].created_at").exists())
                .andExpect(jsonPath("$.[1].updated_at").exists())
                .andExpect(jsonPath("$.[1].text").value("a text"));
    }

    /**
     * Creates two dislciamers and then gets one of them by filtering the text
     */
    @Test
    public void createAndGetDisclaimerByFilterSuccesfullyTest() throws Exception {
        creatBasiceDisclaimer();
        createDisclaimer("Jane Doe", "jane's text", "1.0");

        mockMvc.perform(get("/disclaimer?text=jane"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].name").value("Jane Doe"))
                .andExpect(jsonPath("$.[0].version").value("1.0"))
                .andExpect(jsonPath("$.[0].created_at").exists())
                .andExpect(jsonPath("$.[0].updated_at").exists())
                .andExpect(jsonPath("$.[0].text").value("jane's text"));
    }

    /**
     * Creates two disclaimers and gets none of them by filtering the text
     */
    @Test
    public void createDisclaimersAndFitlerByTextAndGetNoneTest() throws Exception {
        creatBasiceDisclaimer();
        createDisclaimer("Jane Doe", "another text", "1.0");

        MvcResult result = mockMvc.perform(get("/disclaimer?text=nonExistentText"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty())//;
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    private DisclaimerDTO getDisclaimerDTO(String name, String text, String version) {
        DisclaimerDTO disclaimerDTO = new DisclaimerDTO();
        disclaimerDTO.setName(name);
        disclaimerDTO.setText(text);
        disclaimerDTO.setVersion(version);
        return disclaimerDTO;
    }

    private DisclaimerDTO getDisclaimerDTO(Long id, String name, String text,
                                           String version) {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO(name, text, version);
        disclaimerDTO.setId(id);
        return disclaimerDTO;
    }

    private MvcResult creatBasiceDisclaimer() throws Exception {
        return createDisclaimer("John Doe", "a text", "1.0");
    }

    private MvcResult createDisclaimer(String name, String text, String version) throws Exception {
        DisclaimerDTO disclaimerDTO = getDisclaimerDTO(name, text, version);
        return mockMvc.perform(post("/disclaimer")
                        .content(objectMapper.writeValueAsString(disclaimerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}
