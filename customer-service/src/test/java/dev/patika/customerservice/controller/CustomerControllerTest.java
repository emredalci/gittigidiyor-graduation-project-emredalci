package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.enumeration.Status;
import dev.patika.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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
    void should_ReturnResponseEntityCustomerResponseDTO_When_SaveCustomer() {
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
    void should_ReturnResponseEntityCustomerResponseDTO_When_UpdateCustomer() {
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

    @Test
    void should_ReturnResponseEntityCustomerResponseDTO_when_DeleteCustomer() {
        //given
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerService.deleteCustomer(anyString())).thenReturn(expected);
        //when
        String nationalId = "13758028554";
        ResponseEntity<CustomerResponseDTO> actual =customerController.deleteCustomer(nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getClass(),actual.getBody().getClass()),
                ()-> assertEquals(expected.getId(),actual.getBody().getId()),
                ()-> assertEquals(expected.getFirstName(),actual.getBody().getFirstName()),
                ()-> assertEquals(expected.getLastName(),actual.getBody().getLastName()),
                ()-> assertEquals(expected.getNationalId(),actual.getBody().getNationalId()),
                ()-> assertEquals(expected.getPhoneNumber(),actual.getBody().getPhoneNumber()),
                ()-> assertEquals(expected.getIncome(),actual.getBody().getIncome())
        );
    }

    @Test
    void should_ReturnResponseEntityString_When_ApplyForLoan() {
        //given
        CreditResult creditResult = new CreditResult();
        creditResult.setCreditLimit(1000);
        creditResult.setStatus(Status.ACCEPT);
        String expected = creditResult.toString();
        when(customerService.applyForLoan(any())).thenReturn(expected);
        //when
        String nationalId= "13758028554";
        ResponseEntity<String> actual = customerController.applyForLoan(nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getClass(),actual.getBody().getClass()),
                ()-> assertEquals(expected,actual.getBody())

        );
    }

    @Test
    void should_ReturnResponseEntityListCustomerResponseDTO_When_FindAllCustomers(){
        //given
        List<CustomerResponseDTO> expected = new ArrayList<>();
        when(customerService.findAllCustomers()).thenReturn(expected);
        //when
        ResponseEntity<List<CustomerResponseDTO>> actual = customerController.findAllCustomers();

        //then
        assertAll(
                ()-> assertEquals(expected.size(),actual.getBody().size())
        );


    }
}