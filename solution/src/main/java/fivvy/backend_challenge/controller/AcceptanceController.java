package fivvy.backend_challenge.controller;
import fivvy.backend_challenge.dto.AcceptanceDTO;
import fivvy.backend_challenge.service.AcceptanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for acceptance
 */
@RestController
@RequestMapping("/acceptance")
@Slf4j
@AllArgsConstructor
public class AcceptanceController {
    private final AcceptanceService acceptanceService;

    /**
     * Creates an acceptance
     *
     * @param acceptanceDTO
     * @return ResponseEntity:
     * * * CREATED(201) - When successfully created.
     */
    @PostMapping
    public ResponseEntity<AcceptanceDTO> createDisclaimer(@RequestBody AcceptanceDTO acceptanceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(acceptanceService.createAcceptance(acceptanceDTO));
    }
}
