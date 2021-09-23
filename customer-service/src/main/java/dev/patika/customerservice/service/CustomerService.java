package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.exception.CustomerIsAlreadyExistException;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.repository.CustomerRepository;
import dev.patika.customerservice.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements BaseService<CustomerService>{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    public CustomerResponseDTO saveCustomer(CustomerDTO customerDTO){
        logger.info("Customer Service saving process is started");
        boolean isExist = customerRepository.existsByNationalId(customerDTO.getNationalId());
        if(isExist){
            logger.error("Customer service saving process error : "+ ErrorMessage.CUSTOMER_IS_ALREADY_EXIST);
            throw new CustomerIsAlreadyExistException(ErrorMessage.CUSTOMER_IS_ALREADY_EXIST);
        }
        Customer customer=customerMapper.mapFromCustomerDTOtoCustomer(customerDTO);
        customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(customer);
        logger.info("Customer Service saving process is done successfully : " +customerResponseDTO.toString());
        return  customerResponseDTO;

    }


}
