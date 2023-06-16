package fivvy.backend_challenge.repository;

import fivvy.backend_challenge.model.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptanceRepository extends JpaRepository<Acceptance, Long> {
}