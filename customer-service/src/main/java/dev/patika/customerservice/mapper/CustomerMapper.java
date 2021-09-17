package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer mapFromCustomerDTOtoCustomer(CustomerDTO customerDTO);
    Customer mapFromCustomerUpdateDTOtoCustomer(CustomerUpdateDTO customerUpdateDTO);
    CustomerResponseDTO mapFromCustomertoCustomerResponseDTO(Customer customer);
}
