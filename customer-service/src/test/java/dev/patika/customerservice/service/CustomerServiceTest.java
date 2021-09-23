package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerService customerService;



    @Test
    void saveCustomer() {
        Customer customer = new Customer();
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerRepository.existsByNationalId(null)).thenReturn(Boolean.FALSE);
        when(customerMapper.mapFromCustomerDTOtoCustomer(any())).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapFromCustomertoCustomerResponseDTO(customer)).thenReturn(expected);
        //when
        CustomerDTO customerDTO = new CustomerDTO();
        CustomerResponseDTO actual =customerService.saveCustomer(customerDTO);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                () ->assertNotNull(expected),
                ()-> assertEquals(expected.getClass(),actual.getClass()),
                ()-> assertEquals(expected.getFirstName(),actual.getFirstName()),
                ()-> assertEquals(expected.getLastName(),actual.getLastName()),
                ()-> assertEquals(expected.getNationalId(),actual.getNationalId()),
                ()-> assertEquals(expected.getPhoneNumber(),actual.getPhoneNumber()),
                ()-> assertEquals(expected.getIncome(),actual.getIncome())
        );
    }

    @Test
    void updateCustomer() {
        //given
        Customer customer = new Customer();
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.mapFromCustomertoCustomerResponseDTO(customer)).thenReturn(expected);
        //when
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO();
        String nationalId = "13758028554";
        CustomerResponseDTO actual =customerService.updateCustomer(customerUpdateDTO,nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getId(),actual.getId())
        );
    }

}