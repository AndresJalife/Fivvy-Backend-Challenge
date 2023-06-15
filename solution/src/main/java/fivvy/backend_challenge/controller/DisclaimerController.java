package fivvy.backend_challenge.controller;

import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.exception.disclaimer.DisclaimerNotFoundException;
import fivvy.backend_challenge.service.DisclaimerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for disclaiemrs
 */
@RestController
@RequestMapping("/disclaimer")
@Slf4j
@AllArgsConstructor
public class DisclaimerController {
    private final DisclaimerService disclaimerService;

    /**
     * Creates a disclaimer
     *
     * @param disclaimerDTO
     * @return ResponseEntity:
     * * * CREATED(201) - When successfully created.
     */
    @PostMapping
    public ResponseEntity<DisclaimerDTO> createDisclaimer(@RequestBody DisclaimerDTO disclaimerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disclaimerService.createDisclaimer(disclaimerDTO));
    }

    /**
     * Deletes a disclaimer
     *
     * @param id the id of the disclaimer to delete
     * @return ResponseEntity:
     * * * CREATED(201) - When successfully created.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DisclaimerDTO> deleteDisclaimer(@PathVariable Long id) throws DisclaimerNotFoundException {
        return ResponseEntity.ok().body(disclaimerService.deleteDisclaimer(id));
    }
}
