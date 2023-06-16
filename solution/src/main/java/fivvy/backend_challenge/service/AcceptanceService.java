package fivvy.backend_challenge.service;

import fivvy.backend_challenge.dto.AcceptanceDTO;
import fivvy.backend_challenge.model.Acceptance;
import fivvy.backend_challenge.repository.AcceptanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for acceptances
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AcceptanceService {
    private final AcceptanceRepository acceptanceRepository;

    /**
     * Creates an acceptance
     *
     * @param acceptanceDTO the acceptance to create
     * @return the created acceptance
     */
    @Transactional
    public AcceptanceDTO createAcceptance(AcceptanceDTO acceptanceDTO) {
        log.info("Creating acceptance");
        System.out.println(acceptanceDTO);
        Acceptance acceptance = acceptanceRepository.save(new Acceptance(acceptanceDTO));
        System.out.println(acceptance);
        return new AcceptanceDTO(acceptance);
    }
}
