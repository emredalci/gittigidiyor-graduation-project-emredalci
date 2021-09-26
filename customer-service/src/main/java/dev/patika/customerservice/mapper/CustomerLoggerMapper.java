package dev.patika.customerservice.mapper;

import dev.patika.customerservice.dto.CustomerLoggerResponseDTO;
import dev.patika.customerservice.model.CustomerLogger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerLoggerMapper {

    CustomerLoggerMapper INSTANCE = Mappers.getMapper(CustomerLoggerMapper.class);

    CustomerLoggerResponseDTO mapFromCustomerLoggertoCustomerLoggerResponseDTO(CustomerLogger customerLogger);
}
