package dev.patika.customerservice.repository;

import dev.patika.customerservice.model.ErrorResponse;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ErrorResponseRepositoryTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    ErrorResponseRepository errorResponseRepository;

    @InjectMocks
    private ErrorResponse errorResponse;

    @BeforeEach
    public void setUp(){
        errorResponse = new ErrorResponse(400,"Error");
        errorResponse.setId(1L);
        errorResponse.setCreatedDate(Instant.now());
        errorResponse.setLastModifiedDate(Instant.now());
        errorResponseRepository.save(errorResponse);
    }

    @AfterEach
    public void cleanUp(){
        errorResponse = null;
    }

    @Test
    void should_ReturnListErrorResponse_When_FindAll() {
        //given
        //when
        List<ErrorResponse> expected=errorResponseRepository.findAll();
        //then
        assertNotNull(expected);
    }

    @Test
    void should_ReturnListErrorResponse_When_FindAllByStatus() {
        //given
        //when
        List<ErrorResponse> expected = errorResponseRepository.findAllByStatus(400);
        //then
        assertNotNull(expected);
    }

}
