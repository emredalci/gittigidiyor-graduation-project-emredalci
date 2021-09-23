package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByNationalId(String nationalId);

    Optional<Customer> findByNationalId(String nationalId);

    void deleteByNationalId(String nationalId);
}
