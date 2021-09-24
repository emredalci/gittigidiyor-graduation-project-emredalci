package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.model.CreditResult;
import dev.patika.customerservice.model.enumeration.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreditResultMapperTest {

    @InjectMocks
    CreditResult creditResult;

    @Test
    public void shouldMapCreditResulttoCreditResultResponseDTOTest(){
        //given
        CreditResult creditResult = new CreditResult(Status.ACCEPT,1000D,"13758028554");
        //when
        CreditResultResponseDTO creditResultResponseDTO = CreditResultMapper.INSTANCE.mapFromCreditResulttoCreditResultResponseDTO(creditResult);
        //then
        assertAll(
                ()->assertNotNull(creditResultResponseDTO),
                ()->assertEquals(1000D,creditResultResponseDTO.getCreditLimit()),
                ()->assertEquals("13758028554",creditResultResponseDTO.getCustomerNationalId())

        );

    }
}
