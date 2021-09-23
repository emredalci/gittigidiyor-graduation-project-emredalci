package dev.patika.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ErrorResponseValidation extends AbstractBaseEntity{
    private int status;
    private String field;
    private String rule;
}
