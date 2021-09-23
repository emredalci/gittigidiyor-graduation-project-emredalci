package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }
}
