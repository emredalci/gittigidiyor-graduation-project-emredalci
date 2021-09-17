package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer mapFromCustomerDTOtoCustomer(CustomerDTO customerDTO);
    CustomerResponseDTO mapFromCustomertoCustomerResponseDTO(Customer customer);
}
