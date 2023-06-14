package fivvy.backend_challenge.repository;

import fivvy.backend_challenge.model.Disclaimer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisclaimerRepository extends JpaRepository<Disclaimer, Long> {
}