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
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
     * Creates a disclaimer with a name
     */
    private Integer createDisclaimer() throws Exception {
        DisclaimerDTO disclaimerDTO = new DisclaimerDTO();
        disclaimerDTO.setName("a name");
        disclaimerDTO.setText("a text");

        MvcResult mvcResult = mockMvc.perform(post("/disclaimer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disclaimerDTO)))
                .andExpect(status().isCreated())
                .andReturn();


        return JsonPath.read(mvcResult.getResponse().getContentAsString(),
                "$.id");
    }

    /**
     * Create acceptance with a disclaimer.
     * Returns the ResultActions
     */
    private ResultActions createAcceptance(Long disclaimerId, Long userId) throws Exception {
        AcceptanceDTO acceptanceDTO = new AcceptanceDTO();
        acceptanceDTO.setDisclaimerId(disclaimerId.longValue());
        acceptanceDTO.setUserId(userId.longValue());

        return mockMvc.perform(post("/acceptance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(acceptanceDTO)));
    }

    /**
     * Tests the creation of an acceptance
     */
    @Test
    public void createAcceptanceSuccesfullyTest() throws Exception {
        Integer disclaimerId = createDisclaimer();
        createAcceptance(disclaimerId.longValue(), 1L)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.disclaimer_id").value(disclaimerId))
                .andExpect(jsonPath("$.user_id").value(1L))
                .andExpect(jsonPath("$.create_at").exists());
    }

    /**
     * Tests the creation of an acceptance with a non existing disclaimer
     */
    @Test
    public void createAcceptanceWithNonExistingDisclaimerTest() throws Exception {
        createAcceptance(1L, 1L)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Disclaimer not found"));
    }

    /**
     * Create tww acceptances and list them
     */
    @Test
    public void listAcceptancesSuccessfullyTest() throws Exception {
        Integer disclaimerId = createDisclaimer();

        createAcceptance(disclaimerId.longValue(), 1L);
        createAcceptance(disclaimerId.longValue(), 2L);

        mockMvc.perform(get("/acceptance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].disclaimer_id").value(disclaimerId))
                .andExpect(jsonPath("$.[0].user_id").value(1L))
                .andExpect(jsonPath("$.[0].create_at").exists())
                .andExpect(jsonPath("$.[1].disclaimer_id").value(disclaimerId))
                .andExpect(jsonPath("$.[1].user_id").value(2L))
                .andExpect(jsonPath("$.[1].create_at").exists());
    }
}
