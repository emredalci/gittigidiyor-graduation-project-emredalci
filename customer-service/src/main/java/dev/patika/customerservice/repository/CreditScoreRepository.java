package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditScoreRepository extends JpaRepository<CreditScore,Long> {
    @Query("SELECT c.score  FROM CreditScore c WHERE c.lastNumber =?1")
    double findCreditScore(char lastNumber);
}
