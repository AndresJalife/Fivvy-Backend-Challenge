package fivvy.backend_challenge.service;

import fivvy.backend_challenge.dto.DisclaimerDTO;
import fivvy.backend_challenge.exception.DisclaimerNotFoundException;
import fivvy.backend_challenge.exception.IdMissingException;
import fivvy.backend_challenge.model.Disclaimer;
import fivvy.backend_challenge.repository.DisclaimerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public DisclaimerDTO updateDisclaimer(DisclaimerDTO disclaimerDTO) throws DisclaimerNotFoundException, IdMissingException {
        log.info("Updating disclaimer");
        if (disclaimerDTO.getId() == null) {
            throw new IdMissingException();
        }
        Disclaimer disclaimer = disclaimerRepository
                .findById(disclaimerDTO.getId())
                .orElseThrow(DisclaimerNotFoundException::new);
        disclaimer.update(disclaimerDTO);
        disclaimerRepository.save(disclaimer);
        return new DisclaimerDTO(disclaimer);
    }

    /**
     * Gets a disclaimer
     *
     * @param id the id of the disclaimer to get
     * @return the disclaimer
     */
    @Transactional(readOnly = true)
    public DisclaimerDTO getDisclaimerById(Long id) throws DisclaimerNotFoundException {
        log.info("Getting disclaimer");
        Disclaimer disclaimer = disclaimerRepository
                .findById(id)
                .orElseThrow(DisclaimerNotFoundException::new);
        return new DisclaimerDTO(disclaimer);
    }

    /**
     * Gets all disclaimers
     * If text is provided, it will filter the disclaimers by text.
     *
     * @return all disclaimers
     */
    public List<DisclaimerDTO> getAllDisclaimers(String text) {
        log.info("Getting all disclaimers");
        List<Disclaimer> disclaimers;
        if (text != null) {
            disclaimers = disclaimerRepository.findAllByTextContains(text);
        } else {
            disclaimers = disclaimerRepository.findAll();
        }

        return disclaimers.stream()
                .map(DisclaimerDTO::new)
                .toList();
    }
}
