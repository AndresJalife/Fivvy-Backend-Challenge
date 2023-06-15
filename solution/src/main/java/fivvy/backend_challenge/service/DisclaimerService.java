package fivvy.backend_challenge.service;

import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.exception.disclaimer.DisclaimerNotFoundException;
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
     *
     * @param disclaimerDTO the disclaimer to create
     * @return the created disclaimer
     */
    @Transactional
    public DisclaimerDTO createDisclaimer(DisclaimerDTO disclaimerDTO) {
        log.info("Creating disclaimer");
        Disclaimer disclaimer = disclaimerRepository.save(new Disclaimer(disclaimerDTO));
        return new DisclaimerDTO(disclaimer);

    }

    /**
     * Deletes a disclaimer
     *
     * @param id the id of the disclaimer to delete
     * @return the deleted disclaimer
     */
    @Transactional
    public DisclaimerDTO deleteDisclaimer(Long id) throws DisclaimerNotFoundException {
        log.info("Deleting disclaimer");
        Disclaimer disclaimer = disclaimerRepository
                .findById(id)
                .orElseThrow(DisclaimerNotFoundException::new);
        disclaimerRepository.delete(disclaimer);
        return new DisclaimerDTO(disclaimer);
    }

    /**
     * Updates a disclaimer
     *
     * @param disclaimerDTO the disclaimer to update
     * @return the updated disclaimer
     */
    @Transactional
    public DisclaimerDTO updateDisclaimer(DisclaimerDTO disclaimerDTO) throws DisclaimerNotFoundException {
        log.info("Updating disclaimer");
        Disclaimer disclaimer = disclaimerRepository
                .findById(disclaimerDTO.getId())
                .orElseThrow(DisclaimerNotFoundException::new);
        disclaimer.update(disclaimerDTO);
        disclaimerRepository.save(disclaimer);
        return new DisclaimerDTO(disclaimer);
    }
}
