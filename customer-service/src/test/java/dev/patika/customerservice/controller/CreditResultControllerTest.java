package dev.patika.customerservice.controller;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.service.CreditResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditResultControllerTest {

    @Mock
    CreditResultService creditResultService;

    @InjectMocks
    CreditResultController creditResultController;

    @Test
    void should_ReturnResponseEntityListCreditResultResponseDTO_When_FindCreditResult() {
        //given
        List<CreditResultResponseDTO> expected = new ArrayList<>();
        when(creditResultService.findByNationalId(anyString())).thenReturn(expected);
        //when
        String nationalId = "13758028554";
        ResponseEntity<List<CreditResultResponseDTO>> actual=creditResultController.findCreditResult(nationalId);
        //then
        assertAll(
                ()-> assertNotNull(expected),
                ()-> assertNotNull(actual.getBody()),
                ()-> assertEquals(expected.size(),actual.getBody().size())
        );
    }
}