package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CreditResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditResultRepository extends JpaRepository<CreditResult,Long> {
    List<CreditResult> findAllByCustomerNationalId(String nationalId);
}
