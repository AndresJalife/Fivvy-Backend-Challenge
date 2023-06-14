package fivvy.backend_challenge.service;

import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.model.Disclaimer;
import fivvy.backend_challenge.repository.DisclaimerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for disclaimers
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DisclaimerService {
    private final DisclaimerRepository disclaimerRepository;


    /**
     * Creates a disclaimer
     * @param disclaimerDTO the disclaimer to create
     * @return the created disclaimer
     */
    @Transactional
    public DisclaimerDTO createDisclaimer(DisclaimerDTO disclaimerDTO) {
        log.info("Creating disclaimer");
        Disclaimer disclaimer = disclaimerRepository.save(new Disclaimer(disclaimerDTO));
        return new DisclaimerDTO(disclaimer);

    }
}
