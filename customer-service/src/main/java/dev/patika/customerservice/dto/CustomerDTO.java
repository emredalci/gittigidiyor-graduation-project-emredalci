package dev.patika.customerservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "Emre")
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "/^([^0-9]*)$/")
    private String firstName;

    @ApiModelProperty(example = "Dalcı")
    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "/^([^0-9]*)$/")
    private String lastName;

    @ApiModelProperty(example = "11111111111")
    @NotBlank(message = "National id is mandatory")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    private String nationalId;

    @ApiModelProperty(example = "1000")
    @NotNull(message = "Income is mandatory")
    @Pattern(regexp = "/^[0-9]*$/")
    private double income;

    @ApiModelProperty(example = "05391111111")
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    private String phoneNumber;


}
