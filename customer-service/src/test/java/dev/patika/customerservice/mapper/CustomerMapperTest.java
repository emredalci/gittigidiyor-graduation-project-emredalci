package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CustomerDTO;
import dev.patika.customerservice.dto.CustomerResponseDTO;
import dev.patika.customerservice.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerMapperTest {

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    CustomerDTO customerDTO;

    @Test
    public void shouldMapCustomerDTOtoCustomer(){
        //given

        CustomerDTO customerDTO = new CustomerDTO(1L,"Emre"
                ,"Dalcı","13758028554",1000D,"05395064707");


        //when
        Customer customer = CustomerMapper.INSTANCE.mapFromCustomerDTOtoCustomer(customerDTO);
        //then
        assertAll(
                ()-> assertNotNull(customer),
                ()-> assertEquals("Emre",customer.getFirstName()),
                ()-> assertEquals("Dalcı",customer.getLastName()),
                ()-> assertEquals("13758028554",customer.getNationalId()),
                ()-> assertEquals(1000D,customer.getIncome()),
                ()-> assertEquals("05395064707",customer.getPhoneNumber())
        );
    }

    @Test
    public void shouldMapCustomertoCustomerResponseDTO(){
        //given

        Customer customer = new Customer("Emre","Dalcı","13758028554",1000D,"05395064707");


        //when
        CustomerResponseDTO customerResponseDTO = CustomerMapper.INSTANCE.mapFromCustomertoCustomerResponseDTO(customer);
        //then
        assertAll(
                ()-> assertNotNull(customerResponseDTO),
                ()-> assertEquals("Emre",customerResponseDTO.getFirstName()),
                ()-> assertEquals("Dalcı",customerResponseDTO.getLastName()),
                ()-> assertEquals("13758028554",customerResponseDTO.getNationalId()),
                ()-> assertEquals(1000D,customerResponseDTO.getIncome()),
                ()-> assertEquals("05395064707",customerResponseDTO.getPhoneNumber())
        );

    }
}
