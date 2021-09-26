package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CustomerLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerLoggerRepositoryTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    CustomerLoggerRepository customerLoggerRepository;

    @InjectMocks
    private CustomerLogger customerLogger;

    @BeforeEach
    public void setUp(){
        customerLogger.setNationalId("13758028554");
        customerLogger.setPhoneNumberAfter("05395064707");
        customerLogger.setPhoneNumberBefore("05395064707");
        customerLogger.setIncomeAfter(1000D);
        customerLogger.setIncomeBefore(1000D);
        customerLogger.setLastNameAfter("Dalcı");
        customerLogger.setLastNameBefore("Dalcı");
        customerLogger.setFirstNameAfter("Emre");
        customerLogger.setFirstNameAfter("Emre");
        customerLogger.setCreatedDate(Instant.now());
        customerLogger.setId(1L);
        customerLoggerRepository.save(customerLogger);
    }

    @AfterEach
    public void cleanUp(){
        customerLogger = null;
        customerLoggerRepository.deleteAll();
    }

    @Test
    void should_ReturnListCustomerLogger_When_FindAllByNationalId(){
        //given
        //when
        List<CustomerLogger> actual  = customerLoggerRepository.findAllByNationalId("13758028554");
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals("13758028554",actual.get(0).getNationalId())
        );
    }
}
