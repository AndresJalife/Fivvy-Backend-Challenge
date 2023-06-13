package fivvy.backend_challenge.controller;

import fivvy.backend_challenge.service.DisclaimerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
