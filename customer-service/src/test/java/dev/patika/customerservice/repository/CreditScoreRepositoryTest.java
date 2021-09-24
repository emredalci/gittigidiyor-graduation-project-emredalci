package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.CreditScore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CreditScoreRepositoryTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    CreditScoreRepository creditScoreRepository;

    @InjectMocks
    CreditScore creditScore;

    @BeforeEach
    public void setUp(){
        creditScore = new CreditScore(1L,'4',1000);
        creditScoreRepository.save(creditScore);
    }

    @AfterEach
    public void cleanUp(){
        creditScore=null;
    }



    @Test
    void should_Double_When_FindCreditScore() {
        //given
        //when
        double expected=creditScoreRepository.findCreditScore(creditScore.getLastNumber());
        //then
        assertNotNull(expected);
        assertEquals(expected,creditScore.getScore());
    }
}
