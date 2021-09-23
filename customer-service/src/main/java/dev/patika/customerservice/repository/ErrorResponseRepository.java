package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.ErrorResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ErrorResponseRepository extends JpaRepository<ErrorResponse,Long> {
    List<ErrorResponse> findAll();
    List<ErrorResponse> findAllByStatus(int status);
}
