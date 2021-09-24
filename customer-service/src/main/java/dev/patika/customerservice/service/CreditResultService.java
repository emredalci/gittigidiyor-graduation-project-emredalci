package dev.patika.customerservice.service;

import dev.patika.customerservice.dto.CreditResultResponseDTO;
import dev.patika.customerservice.mapper.CreditResultMapper;
import dev.patika.customerservice.repository.CreditResultRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditResultService implements BaseService<CreditResultService>{

    private final CreditResultRepository creditResultRepository;
    private final CreditResultMapper creditResultMapper;

    Logger logger = LoggerFactory.getLogger(CreditResultService.class);

    public List<CreditResultResponseDTO> findByNationalId(String nationalId){
        logger.info("Credit result service finding customer by national id process is started.");
        return creditResultRepository.findAllByCustomerNationalId(nationalId)
                .stream().map(creditResultMapper::mapFromCreditResulttoCreditResultResponseDTO)
                .collect(Collectors.toList());
    }

}
