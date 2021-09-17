package dev.patika.creditscoreservice.repository;

import dev.patika.creditscoreservice.model.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore,Long> {
}
