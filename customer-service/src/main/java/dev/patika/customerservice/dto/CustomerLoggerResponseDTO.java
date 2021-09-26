package dev.patika.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoggerResponseDTO {

    private Long id;

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
