package dev.patika.customerservice.dto;


import dev.patika.customerservice.model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResultResponseDTO {
    @NotNull
    private Status status;

    @NotNull
    private double creditLimit;

    @NotNull
    private String customerNationalId;

}
