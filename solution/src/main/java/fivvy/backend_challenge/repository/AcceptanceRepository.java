package fivvy.backend_challenge.repository;

import fivvy.backend_challenge.model.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for acceptances
 */
public interface AcceptanceRepository extends JpaRepository<Acceptance, Long> {
    /**
     * Finds all the acceptances by user id
     *
     * @param userId the user id
     * @return the list of acceptances
     */
    List<Acceptance> findAllByUserId(Long userId);
}