package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    void saveCustomer() {
        //when
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerService.saveCustomer(any())).thenReturn(expected);
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        ResponseEntity<CustomerResponseDTO> actual = customerController.saveCustomer(customerDTO);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getClass(),actual.getBody().getClass()),
                ()-> assertEquals(expected.getFirstName(),actual.getBody().getFirstName()),
                ()-> assertEquals(expected.getLastName(),actual.getBody().getLastName()),
                ()-> assertEquals(expected.getNationalId(),actual.getBody().getNationalId()),
                ()-> assertEquals(expected.getPhoneNumber(),actual.getBody().getPhoneNumber()),
                ()-> assertEquals(expected.getIncome(),actual.getBody().getIncome())

        );

    }

    @Test
    void updateCustomer() {
        //given
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerService.updateCustomer(any(),anyString())).thenReturn(expected);
        //when
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO();
        String nationalId = "13758028554";
        ResponseEntity<CustomerResponseDTO> actual = customerController.updateCustomer(customerUpdateDTO,nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getClass(),actual.getBody().getClass())

        );
    }
}