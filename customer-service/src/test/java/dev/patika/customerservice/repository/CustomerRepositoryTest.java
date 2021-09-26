package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @InjectMocks
    private Customer customer;

    @BeforeEach
    public void setUp(){
        customer = new Customer("Emre","Dalcı","13758028554",100D,"05395064707");
        customer.setId(1L);
        customer.setCreatedDate(Instant.now());
        customer.setLastModifiedDate(Instant.now());
        customerRepository.save(customer);
    }

    @AfterEach
    public void cleanUp(){
        customerRepository.deleteAll();
        customer = null;
    }

    @Test
    void should_ReturnBoolean_When_ExistsByNationalId() {
        //given
        //when
        boolean actual = customerRepository.existsByNationalId(customer.getNationalId());
        //then
        assertTrue(actual);
    }

    @Test
    void should_ReturnOptionalCustomer_When_FindByNationalId() {
        //given
        //when
        Optional<Customer> actual = customerRepository.findByNationalId(customer.getNationalId());
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(actual.get().getNationalId(),customer.getNationalId())
        );
    }
}
