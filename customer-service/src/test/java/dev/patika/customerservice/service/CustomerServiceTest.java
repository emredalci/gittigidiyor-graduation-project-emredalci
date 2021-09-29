package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.dto.CustomerUpdateDTO;
import dev.patika.customerservice.exception.CustomerIsAlreadyExistException;
import dev.patika.customerservice.exception.NotFoundCustomerException;
import dev.patika.customerservice.mapper.CustomerMapper;
import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.Customer;
import dev.patika.customerservice.model.CustomerLogger;
import dev.patika.customerservice.repository.CreditResultRepository;
import dev.patika.customerservice.repository.CreditScoreRepository;
import dev.patika.customerservice.repository.CustomerLoggerRepository;
import dev.patika.customerservice.repository.CustomerRepository;
import dev.patika.customerservice.util.CalculateCreditResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @Mock
    RestTemplate restTemplate;

    @Mock
    CreditScoreRepository creditScoreRepository;

    @Mock
    CreditResultRepository creditResultRepository;

    @Mock
    CustomerLoggerRepository customerLoggerRepository;

    @InjectMocks
    CustomerService customerService;


    @Test
    void should_ReturnCustomerResponseDTO_When_SaveCustomer() {
        //given
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
                ()-> assertEquals(expected.getIncome(),actual.getIncome()),
                ()-> verify(customerMapper).mapFromCustomertoCustomerResponseDTO(customer),
                ()-> verify(customerRepository).save(customer),
                ()-> verify(customerMapper).mapFromCustomerDTOtoCustomer(any(CustomerDTO.class))
        );
    }

    @Test
    void should_ThrowCustomerIsAlreadyExistException_WhenCustomerSave(){
        //given
        when(customerRepository.existsByNationalId(null)).thenReturn(Boolean.TRUE);
        //then
        CustomerDTO customerDTO = new CustomerDTO();
        assertThrows(CustomerIsAlreadyExistException.class,()->customerService.saveCustomer(customerDTO));
    }

    @Test
    void should_ReturnString_When_ApplyForLoan() {
        //given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setIncome(1000D);
        double score = 600;
        CreditResult creditResult= CalculateCreditResult.calculate(score,customer);
        String result="example";
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(creditScoreRepository.findCreditScore(anyChar())).thenReturn(score);
        when(creditResultRepository.save(creditResult)).thenReturn(creditResult);
        when(restTemplate.getForEntity(anyString(),eq(String.class))).thenReturn(new ResponseEntity(result, HttpStatus.OK));
        //when
        String nationalId = "13758028554";
        String actual = customerService.applyForLoan(nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> verify(creditResultRepository).save(creditResult)
        );
    }

    @Test
    void should_ThrowNotFoundCustomerException_WhenNotFoundCustomerInApplyForLoan(){
        assertThrows(NotFoundCustomerException.class,()-> customerService.applyForLoan(anyString()));

    }



    @Test
    void should_ReturnCustomerDTO_When_UpdateCustomer() {
        //given
        Customer customer = new Customer();
        CustomerResponseDTO expected = new CustomerResponseDTO();
        CustomerLogger customerLogger = new CustomerLogger();
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(customerLoggerRepository.save(any(CustomerLogger.class))).thenReturn(customerLogger);
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
                ()-> assertEquals(expected.getId(),actual.getId()),
                ()-> verify(customerRepository).save(customer),
                ()-> verify(customerMapper).mapFromCustomertoCustomerResponseDTO(customer)
        );
    }

    @Test
    void should_ThrowNotFoundCustomerException_WhenNotFoundCustomerInUpdateCustomer(){
        CustomerUpdateDTO customerUpdateDTO = new CustomerUpdateDTO();
        String nationalId = "13758028554";
        assertThrows(NotFoundCustomerException.class,()-> customerService.updateCustomer(customerUpdateDTO,nationalId));

    }


    @Test
    void should_ReturnCustomerResponseDTO_When_DeleteCustomer() {
        //given
        Customer customer = new Customer();
        customer.setNationalId("13758028554");
        CustomerResponseDTO expected = new CustomerResponseDTO();
        when(customerRepository.findByNationalId(anyString())).thenReturn(Optional.of(customer));
        when(customerMapper.mapFromCustomertoCustomerResponseDTO(customer)).thenReturn(expected);
        customerRepository.deleteByNationalId(customer.getNationalId());
        //when
        String nationalId = "13758028554";
        CustomerResponseDTO actual =customerService.deleteCustomer(nationalId);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertNotNull(expected),
                ()-> assertEquals(expected.getId(),actual.getId()),
                ()-> assertEquals(expected.getFirstName(),actual.getFirstName()),
                ()-> assertEquals(expected.getLastName(),actual.getLastName()),
                ()-> assertEquals(expected.getNationalId(),actual.getNationalId()),
                ()-> assertEquals(expected.getPhoneNumber(),actual.getPhoneNumber()),
                ()-> assertEquals(expected.getIncome(),actual.getIncome()),
                ()-> verify(customerMapper).mapFromCustomertoCustomerResponseDTO(customer)

        );
    }

    @Test
    void should_ThrowNotFoundCustomerException_WhenNotFoundCustomerInDeleteByNationalId(){
        String nationalId = "13758028554";
        assertThrows(NotFoundCustomerException.class,()-> customerService.deleteCustomer(nationalId));
    }

    @Test
    void should_ReturnListCustomerResponseDT_When_FindAllCustomers(){
        //given
        Customer customer = new Customer("Emre","Dalcı","13758028554",1000D,"05395064707");
        customer.setId(1L);
        customer.setCreatedDate(Instant.now());
        customer.setLastModifiedDate(Instant.now());
        List<Customer> listOfCustomers = Collections.singletonList(customer);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        when(customerRepository.findAll()).thenReturn(listOfCustomers);
        when(customerMapper.mapFromCustomertoCustomerResponseDTO(any(Customer.class))).thenReturn(customerResponseDTO);
        //when
        List<CustomerResponseDTO> actual=customerService.findAllCustomers();
        //then
        assertAll(
                ()-> assertEquals(1,actual.size()),
                ()-> assertEquals(customerResponseDTO,actual.get(0)),
                ()-> verify(customerMapper).mapFromCustomertoCustomerResponseDTO(customer)
        );
    }


}