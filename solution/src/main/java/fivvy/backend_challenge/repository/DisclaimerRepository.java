package fivvy.backend_challenge.repository;

import fivvy.backend_challenge.model.Disclaimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisclaimerRepository extends JpaRepository<Disclaimer, Long> {
    List<Disclaimer> findAllByTextContains(String text);
}