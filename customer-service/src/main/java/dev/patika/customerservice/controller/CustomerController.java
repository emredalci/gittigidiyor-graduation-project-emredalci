package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerLoggerResponseDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{nationalId}")
    public ResponseEntity<String> applyForLoan(@PathVariable String nationalId){
        return ResponseEntity.ok(customerService.applyForLoan(nationalId));
    }

    @PutMapping("/{nationalId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody @Valid CustomerUpdateDTO customerUpdateDTO, @PathVariable String nationalId){
        return ResponseEntity.ok(customerService.updateCustomer(customerUpdateDTO,nationalId));
    }

    @DeleteMapping("/{nationalId}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable String nationalId){
        return ResponseEntity.ok(customerService.deleteCustomer(nationalId));
    }

    @GetMapping("/log/{nationalId}")
    public ResponseEntity<List<CustomerLoggerResponseDTO>> getAllCustomerLoggers(@PathVariable String nationalId){
        return ResponseEntity.ok(customerService.getAllCustomerLoggersByNationalId(nationalId));
    }
}
