package dev.patika.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.patika.customerservice.config.annotation.Name;
import dev.patika.customerservice.config.annotation.PhoneNumber;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDTO {

    @ApiModelProperty(example = "Emre")
    @NotBlank(message = "First name is mandatory")
    @Name
    private String firstName;

    @ApiModelProperty(example = "Dalcı")
    @NotBlank(message = "Last name is mandatory")
    @Name
    private String lastName;


    @ApiModelProperty(example = "1000")
    @NotNull(message = "Income is mandatory")
    @Range(max = 100000,min = 0,message = "Invalid income")
    @JsonProperty(required = true)
    private Double income;

    @ApiModelProperty(example = "05391111111")
    @NotBlank(message = "Phone number is mandatory")
    @PhoneNumber
    private String phoneNumber;
}
