package fivvy.backend_challenge.controller;

import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.service.DisclaimerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for disclaiemrs
 */
@RestController
@RequestMapping("/disclaimer")
@Slf4j
@AllArgsConstructor
public class DisclaimerController {
    private final DisclaimerService disclaimerService;

    @PostMapping
    public ResponseEntity<DisclaimerDTO> createDisclaimer(@RequestBody DisclaimerDTO disclaimerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disclaimerService.createDisclaimer(disclaimerDTO));
    }
}
