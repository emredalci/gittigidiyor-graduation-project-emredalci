package dev.patika.customerservice.dto;

import dev.patika.customerservice.config.annotation.Name;
import dev.patika.customerservice.config.annotation.NationalId;
import dev.patika.customerservice.config.annotation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoggerResponseDTO {

    private Long id;

    @Name
    @NotBlank
    private String firstNameBefore;

    @Name
    @NotBlank
    private String firstNameAfter;

    @Name
    @NotBlank
    private  String lastNameBefore;

    @Name
    @NotBlank
    private String lastNameAfter;

    @NotNull
    private Double incomeBefore;

    @NotNull
    private Double incomeAfter;

    @PhoneNumber
    @NotBlank
    private String phoneNumberBefore;

    @PhoneNumber
    @NotBlank
    private String phoneNumberAfter;

    @NationalId
    @NotBlank
    private String nationalId;

}
