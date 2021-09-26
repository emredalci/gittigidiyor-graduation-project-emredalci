package dev.patika.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant createdDate = Instant.now();

    private String firstNameBefore;

    private String firstNameAfter;

    private  String lastNameBefore;

    private String lastNameAfter;

    private Double incomeBefore;

    private Double incomeAfter;

    private String phoneNumberBefore;

    private String phoneNumberAfter;

    private String nationalId;
}
