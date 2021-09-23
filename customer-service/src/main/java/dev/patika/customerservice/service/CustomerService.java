package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.exception.CustomerIsAlreadyExistException;
import dev.patika.customerservice.exception.NotFoundCustomerException;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.repository.CustomerRepository;
import dev.patika.customerservice.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerUpdateDTO, String nationalId){
        logger.info("Customer Service update customer process is started");

        Optional<Customer> customer = customerRepository.findByNationalId(nationalId);
        if (!customer.isPresent()){
            logger.error("Customer Service update customer process error" +ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new NotFoundCustomerException(ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        }

        CustomerMapper.INSTANCE.mapFromCustomerUpdateDTOtoCustomer(customerUpdateDTO,customer.get());
        customerRepository.save(customer.get());
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(customer.get());
        logger.info("Customer Service update customer process is done successfully");
        return customerResponseDTO;

    }

    public CustomerResponseDTO deleteCustomer(String nationalId){
        logger.info("Customer Service delete customer process is started");
        Optional<Customer> foundCustomer=customerRepository.findByNationalId(nationalId);
        if(!foundCustomer.isPresent()){
            logger.error("Customer Service delete customer process error : " +ErrorMessage.CUSTOMER_IS_NOT_FOUND);
            throw new NotFoundCustomerException(ErrorMessage.CUSTOMER_IS_NOT_FOUND);
        }
        CustomerResponseDTO customerResponseDTO = customerMapper.mapFromCustomertoCustomerResponseDTO(foundCustomer.get());
        customerRepository.deleteByNationalId(nationalId);
        logger.info("Customer Service delete customer process is done successfully");
        return customerResponseDTO;
    }


}
