package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.model.CreditResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditResultMapper {
    CreditResultMapper INSTANCE = Mappers.getMapper(CreditResultMapper.class);

    CreditResultResponseDTO mapFromCreditResulttoCreditResultResponseDTO(CreditResult creditResult);
}
