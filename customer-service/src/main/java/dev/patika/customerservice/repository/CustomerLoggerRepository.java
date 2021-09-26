package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CustomerLogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLoggerRepository extends JpaRepository<CustomerLogger,Long> {
}
