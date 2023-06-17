package fivvy.backend_challenge.repository;

import fivvy.backend_challenge.model.Disclaimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisclaimerRepository extends JpaRepository<Disclaimer, Long> {

    /**
     * Finds all disclaimers with the given text
     *
     * @param text the text to search
     * @return List<Disclaimer> the list of disclaimers found
     */
    List<Disclaimer> findAllByTextContains(String text);
}