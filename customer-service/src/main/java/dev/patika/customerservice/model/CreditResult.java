package dev.patika.customerservice.model;

import dev.patika.customerservice.model.enumeration.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CreditResult extends AbstractBaseEntity{
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private double creditLimit;

    @Column(nullable = false)
    private String customerNationalId;

    @Override
    public String toString() {
        return "CreditResult{" +
                "status=" + status +
                ", creditLimit=" + creditLimit +
                ", customerNationalId='" + customerNationalId + '\'' +
                '}';
    }
}
