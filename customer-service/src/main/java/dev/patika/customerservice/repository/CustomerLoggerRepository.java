package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CustomerLogger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerLoggerRepository extends JpaRepository<CustomerLogger,Long> {
    List<CustomerLogger> findAllByNationalId(String nationalId);
}
