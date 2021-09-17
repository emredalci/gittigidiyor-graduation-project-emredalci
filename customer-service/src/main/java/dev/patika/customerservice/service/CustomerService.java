package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDTO saveCustomer(CustomerDTO customerDTO){

    }


    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerUpdateDTO){


    }

    public CustomerResponseDTO deleteCustomer(CustomerDTO customerDTO){

        
    }

}
