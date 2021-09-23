package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapFromCustomerDTOtoCustomer(CustomerDTO customerDTO);
    void mapFromCustomerUpdateDTOtoCustomer(CustomerUpdateDTO customerUpdateDTO, @MappingTarget Customer customer);
    CustomerResponseDTO mapFromCustomertoCustomerResponseDTO(Customer customer);
}
