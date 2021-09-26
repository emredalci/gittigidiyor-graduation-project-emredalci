package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.ErrorResponseValidation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorResponseValidationRepository extends JpaRepository<ErrorResponseValidation,Long> {
}
