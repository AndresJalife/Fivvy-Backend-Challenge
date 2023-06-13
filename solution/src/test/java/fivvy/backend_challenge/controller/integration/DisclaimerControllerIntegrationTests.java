package fivvy.backend_challenge.controller.integration;

import fivvy.backend_challenge.controller.DisclaimerController;
import lombok.AllArgsConstructor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration Tests for the Disclaimer Controller
 */
@AutoConfigureMockMvc
@SpringBootTest
@AllArgsConstructor
public class DisclaimerControllerIntegrationTests {
    private final DisclaimerController disclaimerController;
}
