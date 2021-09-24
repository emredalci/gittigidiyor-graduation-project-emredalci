package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.mapper.CreditResultMapper;
import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.repository.CreditResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditResultServiceTest {
    @Mock
    CreditResultRepository creditResultRepository;

    @Mock
    CreditResultMapper creditResultMapper;

    @InjectMocks
    CreditResultService creditResultService;


    @Test
    void should_ReturnListCreditResultResponseDTO_When_FindByNationalId() {
        //given
        CreditResult creditResult = new CreditResult();
        List<CreditResult> listOfCreditResult = Collections.singletonList(creditResult);
        CreditResultResponseDTO creditResultResponseDTO = new CreditResultResponseDTO();
        when(creditResultRepository.findAllByCustomerNationalId(anyString())).thenReturn(listOfCreditResult);
        when(creditResultMapper.mapFromCreditResulttoCreditResultResponseDTO(any(CreditResult.class))).thenReturn(creditResultResponseDTO);
        //when
        String nationalId ="13758028554";
        List<CreditResultResponseDTO> actual = creditResultService.findByNationalId(nationalId);
        //then
        assertAll(
                ()-> assertEquals(1,actual.size()),
                ()->assertEquals(creditResultResponseDTO,actual.get(0)),
                ()-> verify(creditResultMapper).mapFromCreditResulttoCreditResultResponseDTO(creditResult)
        );
    }
}