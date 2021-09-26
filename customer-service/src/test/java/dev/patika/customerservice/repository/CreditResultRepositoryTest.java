package dev.patika.customerservice.repository;


import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.enumeration.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CreditResultRepositoryTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    CreditResultRepository creditResultRepository;

    @InjectMocks
    private CreditResult creditResult;

    @BeforeEach
    public void setUp(){
        creditResult = new CreditResult(Status.ACCEPT,5000,"13758028554");
        creditResult.setId(1L);
        creditResult.setCreatedDate(Instant.now());
        creditResult.setLastModifiedDate(Instant.now());
        creditResultRepository.save(creditResult);
    }

    @AfterEach
    public void cleanUp(){
        creditResult = null;
        creditResultRepository.deleteAll();
    }



    @Test
    void should_ReturnListCreditResult_When_FindAllByCustomerNationalId() {
        //given
        //when
        List<CreditResult> actual = creditResultRepository.findAllByCustomerNationalId(creditResult.getCustomerNationalId());
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(creditResult.getStatus(),actual.get(0).getStatus()),
                ()-> assertEquals(creditResult.getCustomerNationalId(),actual.get(0).getCustomerNationalId()),
                ()-> assertEquals(creditResult.getCreditLimit(),actual.get(0).getCreditLimit())
        );
    }
}
