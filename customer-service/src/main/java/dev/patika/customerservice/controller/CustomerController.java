package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{nationalId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody @Valid CustomerUpdateDTO customerUpdateDTO, @PathVariable String nationalId){
        return ResponseEntity.ok(customerService.updateCustomer(customerUpdateDTO,nationalId));
    }

    @DeleteMapping("/{nationalId}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable String nationalId){
        return ResponseEntity.ok(customerService.deleteCustomer(nationalId));
    }
}
