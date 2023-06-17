package fivvy.backend_challenge.service;

import fivvy.backend_challenge.dto.AcceptanceDTO;
import fivvy.backend_challenge.exception.DisclaimerNotFoundException;
import fivvy.backend_challenge.model.Acceptance;
import fivvy.backend_challenge.repository.AcceptanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for acceptances
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AcceptanceService {
    private final AcceptanceRepository acceptanceRepository;
    private final DisclaimerService disclaimerService;

    /**
     * Creates an acceptance
     *
     * @param acceptanceDTO the acceptance to create
     * @return the created acceptance
     */
    @Transactional
    public AcceptanceDTO createAcceptance(AcceptanceDTO acceptanceDTO) throws DisclaimerNotFoundException {
        log.info("Creating acceptance");
        if (!disclaimerService.checkDisclaimerExists(acceptanceDTO.getDisclaimerId())) {
            throw new DisclaimerNotFoundException();
        }
        Acceptance acceptance = acceptanceRepository.save(new Acceptance(acceptanceDTO));
        return new AcceptanceDTO(acceptance);
    }

    /**
     * Lists all the acceptances
     */
    public List<AcceptanceDTO> listAcceptances() {
        log.info("Listing acceptances");
        return acceptanceRepository
                .findAll()
                .stream()
                .map(AcceptanceDTO::new)
                .toList();
    }
}
